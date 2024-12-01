import java.util.Random;

public class NutAndBoltMatcher {

    public static class Nut {
        int size;

        public Nut(int size) {
            this.size = size;
        }
    }

    public static class Bolt {
        int size;

        public Bolt(int size) {
            this.size = size;
        }
    }

    public static class Match {
        Nut nut;
        Bolt bolt;

        public Match(Nut nut, Bolt bolt) {
            this.nut = nut;
            this.bolt = bolt;
        }

        @Override
        public String toString() {
            return "Nut: " + nut.size + ", Bolt: " + bolt.size;
        }
    }

    public static Match[] matchNutsAndBolts(Nut[] nuts, Bolt[] bolts) {
        if (nuts.length == 1) {
            Match[] matches = new Match[1];
            matches[0] = new Match(nuts[0], bolts[0]);
            return matches;
        }

        // Choose a random bolt as the pivot
        Random random = new Random();
        Bolt pivotBolt = bolts[random.nextInt(bolts.length)];

        // Partition the nuts into two piles
        Nut[] smallerNuts = partitionNuts(nuts, pivotBolt, false);
        Nut[] equalNuts = partitionNuts(nuts, pivotBolt, true);
        Nut[] largerNuts = partitionNuts(nuts, pivotBolt, false);

        // Partition the bolts into two piles
        Bolt[] smallerBolts = partitionBolts(bolts, equalNuts[0], false);
        Bolt[] equalBolts = partitionBolts(bolts, equalNuts[0], true);
        Bolt[] largerBolts = partitionBolts(bolts, equalNuts[0], false);

        // Recursively match the smaller and larger piles
        Match[] smallerMatches = matchNutsAndBolts(smallerNuts, smallerBolts);
        Match[] largerMatches = matchNutsAndBolts(largerNuts, largerBolts);

        // Combine the matches
        Match[] matches = new Match[nuts.length];
        System.arraycopy(smallerMatches, 0, matches, 0, smallerMatches.length);
        int offset = smallerMatches.length;
        for (int i = 0; i < equalNuts.length; i++) {
            matches[offset + i] = new Match(equalNuts[i], equalBolts[i]);
        }
        offset += equalNuts.length;
        System.arraycopy(largerMatches, 0, matches, offset, largerMatches.length);

        return matches;
    }

    private static Nut[] partitionNuts(Nut[] nuts, Bolt pivotBolt, boolean equal) {
        int count = 0;
        for (Nut nut : nuts) {
            if ((nut.size < pivotBolt.size && !equal) || (nut.size == pivotBolt.size && equal)) {
                count++;
            }
        }

        Nut[] partitionedNuts = new Nut[count];
        int index = 0;
        for (Nut nut : nuts) {
            if ((nut.size < pivotBolt.size && !equal) || (nut.size == pivotBolt.size && equal)) {
                partitionedNuts[index++] = nut;
            }
        }

        return partitionedNuts;
    }

    private static Bolt[] partitionBolts(Bolt[] bolts, Nut pivotNut, boolean equal) {
        int count = 0;
        for (Bolt bolt : bolts) {
            if ((bolt.size < pivotNut.size && !equal) || (bolt.size == pivotNut.size && equal)) {
                count++;
            }
        }

        Bolt[] partitionedBolts = new Bolt[count];
        int index = 0;
        for (Bolt bolt : bolts) {
            if ((bolt.size < pivotNut.size && !equal) || (bolt.size == pivotNut.size && equal)) {
                partitionedBolts[index++] = bolt;
            }
        }

        return partitionedBolts;
    }

    public static void main(String[] args) {
        Nut[] nuts = new Nut[] { new Nut(1), new Nut(2), new Nut(3), new Nut(4), new Nut(5) };
        Bolt[] bolts = new Bolt[] { new Bolt(5), new Bolt(2), new Bolt(1), new Bolt(3), new Bolt(4) };

        Match[] matches = matchNutsAndBolts(nuts, bolts);

        for (Match match : matches) {
            System.out.println(match);
        }
    }
}
