package inf112.skeleton.app.ui.chat;

public class CommandParser {
    public enum Command{
        SETNAME,
        SETCOLOR,
        SETFONTSTCALE,
        UWU,
        CLEAR,
        SENDINTERNAL,
        EXAMPLEMESSAGES,
        CONNECT,
        SELECTCARD,
        SENDCARDS,
        RESETCARDS,
        INVALID
    }

    public String getCmd(String cmd){
        StringBuilder stringCmd = new StringBuilder();

        for (Character c : cmd.toCharArray()){
            if (c == ' '){
                return stringCmd.toString();
            }else {
                stringCmd.append(c);
            }
        }
        return stringCmd.toString();
    }

    public String getArgs(String cmd){
        StringBuilder stringArg = new StringBuilder();
        boolean atArgs = false;

        for (Character c : cmd.toCharArray()){
            if (c.equals(' ')){
                atArgs = true;
            }else {
                if (atArgs){
                    stringArg.append(c);
                }
            }
        }
        return stringArg.toString();
    }
    public Command parseCommand(String cmd){
        Command command = Command.INVALID;

        switch (cmd){
            case "set-name":
                return Command.SETNAME;
            case "chat-color":
                return Command.SETCOLOR;
            case "font-scale":
                return Command.SETFONTSTCALE;
            case "uwufy":
                return Command.UWU;
            case "clear":
                return Command.CLEAR;
            case "show":
                return Command.SENDINTERNAL;
            case "example-messages":
                return Command.EXAMPLEMESSAGES;
            case "connect":
                return Command.CONNECT;
            case "select-card":
                return Command.SELECTCARD;
            case "send-cards":
                return Command.SENDCARDS;
            case "reset-cards":
                return Command.RESETCARDS;
            default:
                break;
        }
        return command;
    }
}
