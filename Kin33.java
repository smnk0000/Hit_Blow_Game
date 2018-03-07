import java.io.*;

public class Kin33
{
  // TODO
  // 要件は処理性能は問わないため、データ構造をListにして全パターン生成->Hitがあれば絞り込める
　// インタフェース実装
  // 確認用printlnしてるやつ削除
  
  static int KETA = 4;
  // 宣言・初期化
  static String[][] arr = new String[10][KETA];

  static void set(){
    for(int i = 0; i<10; i++){
      for(int j = 0; j<KETA; j++){
        arr[i][j] = String.valueOf(i);
      }
    }
  }

  static void print(){
    for(int i = 0; i<10; i++){
      for(int j = 0; j<KETA; j++){
        System.out.print(String.format("%-5s", arr[i][j]));
      }
      System.out.println();
    }
  }
    public static void main( String[] args )
    {
        System.out.println("==========================");
        System.out.println("=====  数当てします  =====");
        System.out.println("==========================");

        int count = 1;
        String Suji = "9999";
        Integer Atari = 0;
        Integer Oshii = 0;

        set();
        print();

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try{
            while(true){
              System.out.println(count + "回目の照会");
              // 1回目
              if( count ==  1){
                  Suji = firstNumber();
                  System.out.println(Suji + "ですか？");
              }
              // 2回目以降
              else if( count < 16 ){
                  Suji = nextNumber(Suji,Atari,Oshii);
                  System.out.println(Suji+ "ですか？");
              }
              else{
                  System.out.println("16回目です。。。");
                  break;
              }

              String input;
              System.out.print("  同じ桁に同じ数字が指定されている個数 --> ");
              input = br.readLine();
              while(!isNumber(input) || !inRange(input) || input.length() != 1){
                System.out.println("    0～"+KETA+"の数字を入力してください");
                System.out.print("  同じ桁に同じ数字が指定されている個数 --> ");
                input = br.readLine();
              }
              Atari = Integer.parseInt(input);

              System.out.print("  違う桁に同じ数字が指定されている個数 --> ");
              input = br.readLine();
              while(!isNumber(input) || !inRange(input) || input.length() != 1){
                System.out.println("    0～"+KETA+"の数字を入力してください");
                System.out.print("  違う桁に同じ数字が指定されている個数 --> ");
                input = br.readLine();
              }
              Oshii = Integer.parseInt(input);
              count++;
            }
        }catch(IOException e){
          //return "エラーです";
        }finally{
          //return ;
        }
    }

    private static String firstNumber(){
      return "1234";
    }

    private static String nextNumber(String sNumber, int iAtari, int iOshii){
        String str = "";
        String hit_blow_pattern = iAtari + "_" + iOshii;

        // HitとBlowのパターンによって絞り込む
        switch (hit_blow_pattern){
          // 0hit_0blowのとき候補から削除
          case "0_0" :
            int delNum = 0;
            for (int i = 0; i < sNumber.length(); i++) {
                delNum = Character.getNumericValue(sNumber.charAt(i));
                arr[delNum][i] = "";
            }
            print();
            break;

          case "2_0" :

          case "4_0" :
            System.out.println("4_0 : 上がり！");
            break;
          default:
        }

        // 次の候補を決める
        // とりあえずはarrからランダムで4桁セット
        int rand = 0;
        for (int i = 0; i<KETA; i++){
          rand = (int)(Math.random() * 10);
          while(arr[rand][i] == "" || !isUnique(String.valueOf(rand),str)){
            rand = (int)(Math.random() * 10);
          }
          str = str + String.valueOf(rand);
        }
        return str;
    }

    // 数値チェック
    private static boolean isNumber(String checkStr) {
      boolean isDigit = true;
      for (int i = 0; i < checkStr.length(); i++) {
          isDigit = Character.isDigit(checkStr.charAt(i));
          if (!isDigit) {
              break;
          }
      }
      return isDigit;
    }

    // 入力範囲チェック
    private static boolean inRange(String checkStr) {
      boolean inRange = true;
      int checkInt = Integer.parseInt(checkStr);
      if(checkInt < 0 || checkInt > KETA)
        inRange = false;
      return inRange;
    }

    // 重複チェック
    private static boolean isUnique(String checkStr, String targetStr) {
      boolean isUnique = true;
      char cChar = checkStr.charAt(0);
      char[] tChar = targetStr.toCharArray();
          for(char c1 : tChar) {
            if(cChar == c1){
              isUnique = false;
              break;
            }
          }
      return isUnique;
    }
}
