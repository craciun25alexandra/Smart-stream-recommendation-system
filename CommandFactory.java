public class CommandFactory {
    private static CommandFactory factory;
    private CommandFactory() {
    }
    public static CommandFactory getInstance() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    public Command creeazaComanda(String numeComanda) {
        switch (numeComanda) {
            case "ADD":
                return new AddCommand();
            case "LIST":
                return new ListCommand();
            case "LISTEN":
                return new ListenCommand();
            case "DELETE":
                return new DeleteCommand();
            case "RECOMMEND":
                return new RecommendCommand();
            case "SURPRISE":
                return new SurpriseCommand();
            default:
                return null;
        }
    }
}
