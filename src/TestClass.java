

public class TestClass {
    public static void main(String[] args) {
        System.out.println(isCyrillic('р'));
    }
    public  static boolean isCyrillic(char c){
        return Character.UnicodeBlock.CYRILLIC.equals(Character.UnicodeBlock.of(c));
    }
}

