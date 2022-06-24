package pl1.shapes;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cz.it4i.swing_javafx_ui.SimpleDialog;
import javafx.application.Platform;
import javafx.scene.paint.Color;



public class MyColor {
  private static int number = 0;

  private static final Map<String, MyColor> NAMES =
      new LinkedHashMap<String, MyColor>();
  private static final Map<String, MyColor> NAMES_BHC =
      new LinkedHashMap<String, MyColor>();
  private static final Map<Color, MyColor> COLOR =
      new LinkedHashMap<Color, MyColor>();
  private static final List<MyColor> COLORS = new ArrayList<MyColor>(32);

  public static final MyColor BLACK = new MyColor(Color.BLACK, "cerna");

  public static final MyColor BLUE = new MyColor(Color.BLUE, "modra");

  public static final MyColor RED = new MyColor(Color.RED, "cervena");

  public static final MyColor MAGENTA = new MyColor(Color.MAGENTA, "fialova");

  public static final MyColor GREEN = new MyColor(Color.GREEN, "green");

  public static final MyColor AZURE = new MyColor(Color.CYAN, "azurova");

  
  public static final MyColor YELLOW = new MyColor(Color.YELLOW, "zluta");

  
  public static final MyColor WHITE = new MyColor(Color.WHITE, "bila");

  
  public static final MyColor LIGHT_GRAY =
      new MyColor(Color.LIGHTGRAY, "svetleseda"); //128 = 0x80

  
  public static final MyColor GRAY = new MyColor(Color.GRAY, "seda");

  
  public static final MyColor DARK_GRAY =
      new MyColor(Color.DARKGREY, "tmavoseda"); //64 = 0x40

  
  public static final MyColor PINK = new MyColor(Color.PINK, "ruzova"); //175 = 0xAF

  
  public static final MyColor ORANGE = new MyColor(Color.ORANGE, "oranzova");

  public static final MyColor SILVER =
      new MyColor(0xD8, 0xD8, 0xD8, 0xFF, "stribrna");

  
  public static final MyColor GOLDEN =
      new MyColor(0xFF, 0xE0, 0x00, 0xFF, "zlata");

  
  public static final MyColor BRICKY =
      new MyColor(0xFF, 0x66, 0, 0xFF, "cihlova");

  
  public static final MyColor BROWN =
      new MyColor(0x99, 0x33, 0x00, 0xFF, "brown");

  
  public static final MyColor CREAMY =
      new MyColor(0xFF, 0xFF, 0xCC, 0xFF, "kremova");

  
  public static final MyColor KHAKI =
      new MyColor(0x99, 0x99, 0x00, 0xFF, "khaki");

  
  public static final MyColor STEEL =
      new MyColor(0x00, 0x99, 0xCC, 0xFF, "ocelova");

  
  public static final MyColor OCHRE =
      new MyColor(0xFF, 0x99, 0x00, 0xFF, "okrova");

  //=== Prusvitne barvy

  
  public static final MyColor MILCKY =
      new MyColor(0xFF, 0xFF, 0xFF, 0x80, "mlecna");

  
  public static final MyColor SMOKY =
      new MyColor(0x80, 0x80, 0x80, 0x80, "kourova");

  
  public static final MyColor NONE = new MyColor(0, 0, 0, 0, "zadna");

  static {
    AZURE.addName("blankytna");
  }

  private static boolean upperCase = false;
  private final String name; //Nazev barvy zadavany konstruktoru
  private final Color color; //Barva ze standardni knihovny
  private final int index = number++;

  public int getIndex() {
    return index;
  }

  
  public static MyColor[] getKnownColors() {
    return COLORS.toArray(new MyColor[COLORS.size()]);
  }

  
  public static String[] getKnowNames() {
    String[] nazvy = NAMES.keySet().toArray(new String[NAMES.size()]);
    Arrays.sort(nazvy, Collator.getInstance());
    if (upperCase) {
      for (int i = 0; i < nazvy.length; i++) {
        nazvy[i] = nazvy[i].toUpperCase();
      }
    }
    return nazvy;
  }

  
  public static boolean setUpperCase(boolean upperCase) {
    boolean original = MyColor.upperCase;
    MyColor.upperCase = upperCase;
    return original;
  }

//== OSTATNI NESOUKROME METODY TRIDY ===========================================

  
  public static void printKnownNames() {
    final int MAX_V_RADKU = 64;
    String[] nazvy = getKnowNames();
    StringBuilder sb = new StringBuilder();
    for (int i = 0, vRadku = 0; i < nazvy.length; i++) {
      String text = nazvy[i];
      int textLength = text.length();
      if ((vRadku + textLength) >= MAX_V_RADKU) {
        sb.append('\n');
        vRadku = 0;
      }
      sb.append(text);
      vRadku += textLength + 2;
      if (i < nazvy.length) {
        sb.append(", ");
      }
    }
		Platform.runLater(() -> SimpleDialog.showInformation("Message", sb.toString()));
	  }

  
  public static Collection<MyColor> knownColors() {
    return Collections.unmodifiableList(COLORS);
  }

  
  public static Collection<String> knownNames() {
    return Arrays.asList(getKnowNames());
  }

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

  
  private MyColor(int red, int green, int blue, int alpha, String nazev) {
    this(Color.rgb(red, green, blue, alpha / 255.), nazev);
  }

  
  private MyColor(Color c, String name) {
    color = c;
    this.name = name.toLowerCase();

    if (NAMES.containsKey(name) || COLOR.containsKey(c)) {
      throw new IllegalArgumentException("\nInterni chyba - barva " + getName()
          + " a/nebo " + getCharakteristicDec() + " jiz existuji");
    }

    NAMES.put(name, this);
    COLOR.put(color, this);
    COLORS.add(this);
  }

  public static MyColor getColor(String colorName) {
    MyColor color = NAMES.get(colorName.toLowerCase());
    if (color != null) {
      return color;
    } else {
      throw new IllegalArgumentException("Takto pojmenovanou barvu neznam.");
    }
  }

  
  public static MyColor getColor(int red, int green, int blue) {
    return getColor(red, green, blue, 0xFF);
  }

  
  public static MyColor getColor(int red, int green, int blue, int alpha) {
    Color color = new Color(red, green, blue, alpha);
    MyColor barva = COLOR.get(color);
    if (barva != null) {
      return barva;
    }
    String nazev =
        "Barva(r=" + red + ",g=" + green + ",b=" + blue + ",a=" + alpha + ")";
    return getColor(red, green, blue, alpha, nazev);
  }

  
  public static MyColor getColor(int red, int green, int blue, String name) {
    return getColor(red, green, blue, 0xFF, name);
  }

  
  public static MyColor getColor(int red, int green, int blue, int alpha,
      String name) {
    name = name.trim().toLowerCase();
    if ((name == null) || name.equals("")) {
      throw new IllegalArgumentException(
          "Barva musi mit zadan neprazdny nazev");
    }
    Color color = new Color(red, green, blue, alpha);
    MyColor barvaN = NAMES.get(name);
    MyColor barvaC = COLOR.get(color);

    if ((barvaN != null) && (barvaN == barvaC)) {
      //Je pozadovana jiz existujici barva
      return barvaN;
    }
    if ((barvaN == null) && (barvaC == null)) {
      //Je pozadovana dosud neexistujici barva
      return new MyColor(red, green, blue, alpha, name);
    }
    //Zjistime, s kterou existujici barvou pozadavky koliduji
    MyColor b = (barvaC != null) ? barvaC : barvaN;
    Color c = b.color;
    throw new IllegalArgumentException(
        "\nZadane parametry barvy koliduji s parametry existujici barvy "
            + "[existujici  zadana]:" + "\nnazev:          " + b.getName()
            + "  " + name + "\ncervena slozka: " + c.getRed() + "  " + red
            + "\nzelena  slozka: " + c.getGreen() + "  " + green
            + "\nmodra   slozka: " + c.getBlue() + "  " + blue
            + "\npruhlednost:    " + c.getOpacity() + "  " + alpha);
  }

//== NESOUKROME METODY INSTANCI ================================================

  
  public Color getColor() {
    return color;
  }

  
  public String getCharakteristicDec() {
    return String.format("%s(dec:R=%d,G=%d,B=%d,A=%d)", name, color.getRed(),
        color.getGreen(), color.getBlue(), color.getOpacity());
  }

  
  public String getCharakteristicHex() {
    return String.format("%s(hex:R=%02X,G=%02X,B=%02X,A=%02X)", name,
        color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
  }

  
  public String getName() {
    return (upperCase ? name.toUpperCase() : name);
  }

  
  public String[] getNames() {
    Collection<String> nazvy = names();
    return nazvy.toArray(new String[nazvy.size()]);
  }

  
  public Collection<String> names() {
    Collection<String> nazvy = new ArrayList<String>();
    for (Map.Entry<String, MyColor> entry : NAMES.entrySet()) {
      if (entry.getValue() == this) {
        nazvy.add((upperCase ? entry.getKey().toUpperCase() : entry.getKey()));
      }
    }
    return nazvy;
  }

  
  public void addName(String dalsiNazev) {
    dalsiNazev = dalsiNazev.toLowerCase();
    MyColor b = colorWithName(dalsiNazev);
    if (b == null) {
      NAMES.put(dalsiNazev, this);
    } else {
      throw new IllegalArgumentException("\nJmeno musi byt jedinecne. "
          + "Barva s nazvem " + dalsiNazev + " je jiz definovana.");
    }
  }

  
  @Override
  public String toString() {
    return name;
  }


  
  private static MyColor colorWithName(String name) {
    name = name.toLowerCase();
    MyColor barva = NAMES.get(name);
    if (barva == null) {
      barva = NAMES_BHC.get(name);
    }
    return barva;
  }
  
}
