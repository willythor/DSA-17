import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        int neg_count = 0;
        int pos_count = 0;

        for (int m: A) {
            if (m < 0) {
                neg_count++;
            }
            if (m >= 0) {
                pos_count++;
            }
        }

        int[] negatives = new int[neg_count];
        int[] positives = new int[pos_count];

        int neg_index = 0;
        int pos_index = 0;

        for (int f: A) {
            if (f<0) {
                negatives[neg_index] = f*-1;
                neg_index++;
            }
            else {
                positives[pos_index] = f;
                pos_index++;
            }
        }

        RadixSort.radixSort(negatives,256);

        int j=0;
        for (int i = negatives.length-1; i>=0;i--) {
            A[j] = -negatives[i];
            j++;
        }

        RadixSort.radixSort(positives,256);
        for (int i = 0; i< positives.length;i++) {
            A[i+j] = positives[i];
        }

    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        LinkedList<String>[] L = new LinkedList[26];
        for (int i = 0; i < 26; i++)
            L[i] = new LinkedList<>();
        for (String i : A) {
            // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
            int num = getNthCharacter(i,n);
            L[num].add(i);

        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            while (list.size() != 0) {
                A[j] = list.remove();
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int i = 0; i < stringLength; i++){
            countingSortByCharacter(S,i);
        }
    }

}
