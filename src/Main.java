/*
    References
    C Implementation -> http://www.netlib.no/netlib/toms/382
    Original Paper -> https://dl.acm.org/citation.cfm?id=362502
*/

public class Main {
    public static void main(String[] args) {
        KeyGenerator k = new KeyGenerator(10);
        k.addKey("ashgdjkls");
        k.addKey("dsasndjnasd");
        k.addKey("adsfasdasdasd");
        k.addKey("hjbjknasjknasö");
        k.addKey("nbamsndasdas");
        k.addKey("abnmbsdöas");
        k.addKey("abmnsbdöasd");
        k.addKey("öön12mçmöbv");
        k.addKey("ghjkdnlmad");
        k.addKey("jnösjkmfçds");

        k.generateCombinations();
        while(k.hasNext() == true) {
            System.out.println(k.getNextCombination());
        }

        /*while(k.hasNext(3) == true) {
            System.out.println(k.getNextCombination(3));
        }*/
    }
}

