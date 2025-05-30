
package com.back;

public class Calc {

    static public int run(String s) {
        return calRecursion(removeBracket(s), "\\+");
    }

    // 괄호 처리
    static public String removeBracket(String s){
        StringBuilder sb = new StringBuilder();
        StringBuilder buf = new StringBuilder();
        int depth = 0;

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
                        sb.append(removeBracket(buf.toString()));
                    else
                        sb.append(calRecursion(buf.toString(), "\\+"));

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

    // 사칙 연산 통합
    static public int calRecursion(String str, String op){
        String[] parts = str.split( " " + op + " ");
        int result = 0;
        int buf = 0;
        boolean flag = true;

        for(String p : parts){
            try{
                buf = Integer.parseInt(p.trim());
                if(flag){
                    result = buf;
                    flag = false;
                }
                else{
                    switch(op){
                        case "\\+" -> result += buf;
                        case "-" -> result -= buf;
                        case "\\*" -> result *= buf;
                    }
                }

            }catch(Exception e){
                switch(op){
                    case "\\+" -> buf = calRecursion(p, "-");
                    case "-" -> buf = calRecursion(p, "\\*");
                }

                if(flag){
                    result = buf;
                    flag = false;
                }
                else{
                    switch(op){
                        case "\\+" -> result += buf;
                        case "-" -> result -= buf;
                        case "\\*" -> result *= buf;
                    }
                }

            }
        }

        return result;
    }

}
