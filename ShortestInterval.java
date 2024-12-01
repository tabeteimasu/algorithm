import java.util.Arrays;

public class DocumentSearch {
    public static int[] findShortestInterval(String[] document, String[] query) {
        int n = document.length;
        int m = query.length;
        
        // Initialize dp array and result variables
        int[] dp = new int[n];
        Arrays.fill(dp, -1);  // Initialize dp with -1
        int minLen = Integer.MAX_VALUE;
        int[] resultInterval = {-1, -1};
        
        // Iterate over document words
        for (int i = 0; i < n; i++) {
            if (document[i].equals(query[0])) {
                dp[i] = i;  // Start a new interval for the first query word
            }
            
            for (int j = 1; j < m; j++) {
                if (document[i].equals(query[j])) {
                    // Find the smallest interval ending before i that contains the first j-1 query words
                    for (int k = i - 1; k >= 0; k--) {
                        if (dp[k] != -1 && document[k].equals(query[j - 1])) {
                            dp[i] = dp[k];  // Update the start of the interval
                            break;
                        }
                    }
                }
            }
            
            // Check if this interval contains all m query words
            if (dp[i] != -1 && document[i].equals(query[m - 1])) {
                int intervalLen = i - dp[i] + 1;
                if (intervalLen < minLen) {
                    minLen = intervalLen;
                    resultInterval[0] = dp[i];
                    resultInterval[1] = i;
                }
            }
        }
        
        return resultInterval[0] == -1 ? new int[]{-1, -1} : resultInterval;
    }

    public static void main(String[] args) {
        String[] document = {"this", "is", "a", "test", "document", "is", "a", "test"};
        String[] query = {"is", "a", "test"};
        
        int[] result = findShortestInterval(document, query);
        
        if (result[0] != -1) {
            System.out.println("Shortest interval is from index " + result[0] + " to index " + result[1]);
        } else {
            System.out.println("No valid interval found.");
        }
    }
}
