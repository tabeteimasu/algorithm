public class EggDrop {

    // Version 0: 1 egg, ≤ T tosses.
    public static int version0(int n, int T) {
        for (int i = 1; i <= n; i++) {
            if (i >= T) {
                return i; // Egg breaks at floor T
            }
        }
        return -1; // Should never reach here
    }

    // Version 1: ∼1lg⁡n eggs and ∼1lg⁡n tosses.
    public static int version1(int n, int T) {
        int low = 1, high = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid >= T) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low; // Egg breaks at floor T
    }

    // Version 2: ∼lg⁡T eggs and ∼2lg⁡T tosses.
    public static int version2(int n, int T) {
        int k = 0;
        while ((1 << k) <= n && (1 << k) < T) {
            k++;
        }
        int low = (1 << (k - 1)), high = Math.min(n, (1 << k));
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid >= T) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low; // Egg breaks at floor T
    }

    // Version 3: 2 eggs and ∼2√n tosses.
    public static int version3(int n, int T) {
        int interval = (int) Math.ceil(Math.sqrt(n));
        int previousFloor = 0;
        for (int i = interval; i <= n; i += interval) {
            if (i >= T) {
                for (int j = previousFloor + 1; j <= i; j++) {
                    if (j >= T) {
                        return j; // Egg breaks at floor T
                    }
                }
            }
            previousFloor = i;
        }
        return -1; // Should never reach here
    }

    // Version 4: 2 eggs and ≤ cT tosses for some fixed constant c.
    public static int version4(int n, int T, int c) {
        for (int i = c; i <= n; i += c) {
            if (i >= T) {
                for (int j = i - c + 1; j <= i; j++) {
                    if (j >= T) {
                        return j; // Egg breaks at floor T
                    }
                }
            }
        }
        return -1; // Should never reach here
    }

    public static void main(String[] args) {
        int n = 100;
        int T = 50;
        int c = 5;

        System.out.println("Version 0: " + version0(n, T));
        System.out.println("Version 1: " + version1(n, T));
        System.out.println("Version 2: " + version2(n, T));
        System.out.println("Version 3: " + version3(n, T));
        System.out.println("Version 4: " + version4(n, T, c));
    }
}

