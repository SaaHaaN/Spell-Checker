package projectpackage;

import java.util.Scanner;

public class Functions {

    public BinarySearchTree<String> getWords() { // reading words from the file
        BinarySearchTree<String> wordsBST = new BinarySearchTree<>(); // creating a BST

        String fileName = "/words.txt";
        try (Scanner scanner = new Scanner(getClass().getResourceAsStream(fileName))) { // reading the file

            while (scanner.hasNextLine()) { // reading the file
                String line = scanner.nextLine();
                wordsBST.insert(line.trim()); // adding words to the BST without spaces (trim method)
            }
        }
        return wordsBST;
    }

    //--------------------------------------------------------------------------
    public BinarySearchTree<String> closestWordsByK(BinarySearchTree<String> closestWordsBST, int k) { // getting k closest words
        BinarySearchTree<String> kClosestWords = new BinarySearchTree<>(); // creating a BST

        StringBuilder sb = closestWordsBST.allElementsToStringBuilder(); // getting all elements from the BST

        int count = 0;

        for (String word : sb.toString().split(" ")) { // adding words to the BST
            if (count == k) {
                break;
            }
            kClosestWords.insert(word); // adding words to the BST
            count++;
        }

        return kClosestWords;
    }

    public BinarySearchTree<String> closestWords(BinarySearchTree<String> wordsBST, String word) { // getting closest words
        BinarySearchTree<String> closestWords = new BinarySearchTree<>(); // creating a BST
        traverseAllElements(closestWords, wordsBST, word); // traversing all elements
        return closestWords;
    }

    public String getClosestWord(BinarySearchTree<String> wordsBST, String word) { // getting closest word
        BinarySearchTree<String> closestWords = closestWords(wordsBST, word); // getting closest words (overloaded method)
        return closestWords.root.data;
    }

    //--------------------------------------------------------------------------
    void traverseAllElements(BinarySearchTree<String> closestWords, BinarySearchTree<String> dictBST, String word) { // traversing all elements
        traverseAllElements(closestWords, dictBST.root, word); // traversing all elements (overloaded method)
    }

    private void traverseAllElements(BinarySearchTree<String> closestWords, Node<String> node, String word) { // traversing all elements
        if (node != null) {
            traverseAllElements(closestWords, node.left, word); // traversing all elements

            String data = node.data;
            int distance = levenshteinDistance(data, word); // calculating the distance between the word and the data
            if (distance <= 2) { // if the distance is less than or equal to 2
                closestWords.insert(data); // adding the data to the BST
            }
            traverseAllElements(closestWords, node.right, word); // traversing all elements
        }
    }

    //--------------------------------------------------------------------------
    public int levenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int substitutionCost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + substitutionCost));
            }
        }
        return dp[m][n];
    }
}
