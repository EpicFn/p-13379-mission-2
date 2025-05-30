
package com.back;

public class Calc {

    static public int run(String s) {
        return calLevel1(calLevel0(s));
    }

    // 괄호 처리
    static public String calLevel0(String s){
        StringBuilder sb = new StringBuilder();
        StringBuilder buf = new StringBuilder();
        int depth = 0;
        String op = "";

        for(String c : s.split("")){
            if (c.equals("(")){
                if(depth > 0)
                    buf.append(c);
                depth ++;
            }
            else if(c.equals(")")) {
                depth--;
                if(depth > 0)
                    buf.append(c);
                else if(depth == 0){
                    if(buf.toString().contains("("))
                        sb.append(calLevel0(buf.toString()));
                    else
                        sb.append(String.valueOf(calLevel1(buf.toString())));
                    buf = new StringBuilder();
                }
            }
            else if(depth > 0){
                buf.append(c);
            }
            else
                sb.append(c);
        }

        return sb.toString();
    }

    // plus 처리
    static public int calLevel1(String s){
        String[] parts = s.split( " \\+ ");
        int result = 0;
        int buf;
        boolean plusFlag = true;

        for(String p : parts){
            try{
                buf = Integer.parseInt(p.trim());
                result += buf;

            }catch(Exception e){
                buf = calLevel2(p);
                result += buf;
            }
        }

        return result;
    }

    // minus 처리
    static public int calLevel2(String s) {
        String[] parts = s.split(" - ");
        int result = 0;
        int buf;
        boolean flag = true;

        for(String p : parts){
            try{
                buf = Integer.parseInt(p.trim());
                if(flag){
                    result = buf;
                    flag = false;
                }
                else
                    result -= buf;

            }catch(Exception e){
                buf = calLevel3(p);
                if(flag){
                    result = buf;
                    flag = false;
                }
                else
                    result -= buf;
            }
        }

        return result;
    }

    //multiply 처리
    static public int calLevel3(String s) {
        String[] parts = s.split(" \\* ");
        int result = 0;
        int buf;
        boolean flag = true;

        for(String p : parts){
            try{
                buf = Integer.parseInt(p.trim());
                if(flag){
                    result = buf;
                    flag = false;
                }
                else
                    result *= buf;

            }catch(Exception e){
                buf = calLevel2(p);
                if(flag){
                    result = buf;
                    flag = false;
                }
                else
                    result *= buf;
            }
        }

        return result;
    }




}
