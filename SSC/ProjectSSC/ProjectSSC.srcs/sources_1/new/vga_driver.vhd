library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
use ieee.numeric_std.all;

entity vga_driver is
     port (clk : in  std_logic; 
           hsync : out std_logic;
           vsync : out std_logic;
           R,G : out std_logic_vector (2 downto 0);
           invert : in std_logic;
           brighten : in std_logic;
           binary : in std_logic;
           darken : in std_logic;
           B : out std_logic_vector (1 downto 0));
end vga_driver;

architecture behavioral of vga_driver is

signal clk25 : std_logic := '0'; -- Semnalul de ceas de 25 MHz (frecventa pixelilor)

constant picture_size : integer := 65536; 

--Signals for Block RAM
signal wea : std_logic_vector(0 downto 0) := "0";
signal addra : std_logic_vector(15 downto 0):= (others => '0');
signal dina : std_logic_vector(7 downto 0) := (others => '0');
signal douta : std_logic_vector(7 downto 0):= (others => '0');

constant HD : integer := 639; -- Horizontal Display 
constant HFP : integer := 16; --  Right border (front porch)
constant HSP : integer := 96; -- Sync pulse (retrace)
constant HBP : integer := 48; --  Left border (back porch)

-- HD + HFP + HSP + HBP = 800
	
constant VD : integer := 479;  -- Vertical Display (480)
constant VFP : integer := 10;  -- Right border (front porch)
constant VSP : integer := 2; -- Sync pulse (retrace)
constant VBP : integer := 33; --  Left border (back porch)

-- VD + VHP + VSP + VBP = 525
	
signal hPos : integer := 1;
signal vPos : integer := 0; 

signal inv : std_logic := '0';
signal bri : std_logic := '0';
signal dark : std_logic := '0';
signal bin : std_logic := '0';
signal threshold : std_logic_vector(8 downto 0) := (others => '0');
	
signal videoOn : std_logic := '0';

component blk_mem_gen_0
      port (clka : in std_logic;
            wea : in std_logic_vector(0 downto 0);
            addra : in std_logic_vector(15 downto 0);
            dina : in std_logic_vector(7 downto 0);
            douta : out std_logic_vector(7 downto 0));
end component;

component clk_wizard
    port (clk_out1 : out std_logic;
          clk_in1 : in std_logic);
end component;

begin

clk_divider : clk_wizard
   port map (clk_out1 => clk25,
             clk_in1 => clk);
 
picture_load : blk_mem_gen_0 
    port map (clka => clk25, 
              wea => wea,
              addra => addra, 
              dina => dina, 
              douta => douta);

HPC : process(clk25)
begin
	if clk25'event and clk25 = '1' then
		if hPos = 799 then
			hPos <= 0;
		else
			hPos <= hPos + 1;
		end if;
	end if;
end process;

VPC : process(clk25, hPos)
begin
	if clk25'event and clk25 = '1' then
		if hPos = 799 then
			if vPos = 524 then
				vPos <= 0;
			else
				vPos <= vPos + 1;
			end if;
		end if;
	end if;
end process;

HorizontalSync : process(clk25, hPos)
begin
	if clk25'event and clk25 = '1' then
		if((hPos <= (HD + HFP)) OR (hPos > HD + HFP + HSP))then		
			hsync <= '1';
		else
			hsync <= '0';
		end if;
	end if;
end process;

VerticalSync: process(clk25, vPos)
begin
	if clk25'event and clk25 = '1' then
		if((vPos <= (VD + VFP)) OR (vPos > VD + VFP + VSP))then		
			vsync <= '1';
		else
			vsync <= '0';
		end if;
	end if;
end process;

Video : process(clk25, hPos, vPos)
begin
	if clk25'event and clk25 = '1' then
		if hPos <= HD and vPos <= VD then
			videoOn <= '1';
		else  
			videoOn <= '0';
		end if;
	end if;
end process;

draw : process(clk25, hPos, vPos, videoOn)
begin
	if clk25'event and clk25 = '1' then
		if videoOn = '1' then
            if unsigned(addra) < picture_size then
                    R <= douta(7 downto 5); 
                    G <= douta(4 downto 2); 
                    B <= douta(1 downto 0);
                    
                    if invert = '1' and brighten  = '0' and darken = '0' and bin = '0' then
                        R <= "111" - douta(7 downto 5); 
                        G <= "111" - douta(4 downto 2); 
                        B <= "11" - douta(1 downto 0);
                    end if;
                    
                    if brighten  = '1' and invert = '0' and darken = '0' and bin = '0' then
                        if douta(7 downto 5) < "100" then
                            R <= douta(6 downto 5) & "0" ;
                        else
                            R <= "111";
                        end if;
                        if douta(4 downto 2) < "100" then
                            G <= douta(3 downto 2) & "0" ;
                        else
                            G <= "111";
                        end if;
                        if douta(1 downto 0) < "10" then
                            B <= douta(0) & "0" ;
                        else
                            B <= "11";
                        end if;
                    end if;
                    
                    if invert = '0' and brighten  = '0' and darken = '1' and bin = '0' then
                        R <= "0" &  douta(7 downto 6); 
                        G <= "0" & douta(4 downto 3); 
                        B <= "0" & douta(1);
                    end if;
                    
                    if invert = '0' and brighten = '0' and darken = '0' and bin = '1' then
                        threshold <= douta(7 downto 5) * "100000" + douta(4 downto 2) * "100" + douta(1 downto 0);
                        if conv_integer(threshold) > 139 then
                            R <= (others => '1');
                            G <= (others => '1');
                            B <= (others => '1');
                        else
                            R <= (others => '0');
                            G <= (others => '0');
                            B <= (others => '0');
                        end if;
                    end if;
                    
                addra <= std_logic_vector(unsigned(addra)+1);
                if hPos = HD and vPos = VD then
                    addra <= (others => '0');
                end if;
			else
				    R <= (others => '0');
				    G <= (others => '0');
				    B <= (others => '0');
			end if;
			else
                R <= (others => '0');
                G <= (others => '0');
                B <= (others => '0');
		end if;		
	end if;
end process;

inv <= invert;
bri <= brighten;
dark <= darken;
bin <= binary;

end behavioral;
