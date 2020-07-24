import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;

public class Command {

    //private String[] command;

    private static String getLine() throws IOException {
        String line = "";
        char ch;
        do {
            ch = (char) System.in.read();
            line += ch;
        } while (ch != '\n');
        return line;
    }

    public static String[] getCommandArgs() throws IOException {
        String line = getLine();

        String[] command= line.split(" ");

        char[] lastLine = command[command.length-1].toCharArray();
        String endLine = "";
        for( int i=0; i<(lastLine.length-1); i++){
            endLine+=lastLine[i];
        }
        command[command.length-1] = endLine;
        return command;
    }



}
