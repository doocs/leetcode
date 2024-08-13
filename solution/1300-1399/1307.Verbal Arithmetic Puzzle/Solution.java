class Solution {
    private boolean isAnyMapping(List<String> words, int row, int col, int bal,
        HashMap<Character, Integer> letToDig, char[] digToLet, int totalRows, int totalCols) {
        // If traversed all columns.
        if (col == totalCols) {
            return bal == 0;
        }

        // At the end of a particular column.
        if (row == totalRows) {
            return (bal % 10 == 0
                && isAnyMapping(
                    words, 0, col + 1, bal / 10, letToDig, digToLet, totalRows, totalCols));
        }

        String w = words.get(row);

        // If the current string 'w' has no character in the ('col')th index.
        if (col >= w.length()) {
            return isAnyMapping(words, row + 1, col, bal, letToDig, digToLet, totalRows, totalCols);
        }

        // Take the current character in the variable letter.
        char letter = w.charAt(w.length() - 1 - col);

        // Create a variable 'sign' to check whether we have to add it or subtract it.
        int sign = (row < totalRows - 1) ? 1 : -1;

        // If we have a prior valid mapping, then use that mapping.
        // The second condition is for the leading zeros.
        if (letToDig.containsKey(letter)
            && (letToDig.get(letter) != 0 || (letToDig.get(letter) == 0 && w.length() == 1)
                || col != w.length() - 1)) {

            return isAnyMapping(words, row + 1, col, bal + sign * letToDig.get(letter), letToDig,
                digToLet, totalRows, totalCols);

        } else {
            // Choose a new mapping.
            for (int i = 0; i < 10; i++) {
                // If 'i'th mapping is valid then select it.
                if (digToLet[i] == '-'
                    && (i != 0 || (i == 0 && w.length() == 1) || col != w.length() - 1)) {
                    digToLet[i] = letter;
                    letToDig.put(letter, i);

                    // Call the function again with the new mapping.
                    if (isAnyMapping(words, row + 1, col, bal + sign * letToDig.get(letter),
                            letToDig, digToLet, totalRows, totalCols)) {
                        return true;
                    }

                    // Unselect the mapping.
                    digToLet[i] = '-';
                    letToDig.remove(letter);
                }
            }
        }

        // If nothing is correct then just return false.
        return false;
    }

    public boolean isSolvable(String[] wordsArr, String result) {
        // Add the string 'result' in the list 'words'.
        List<String> words = new ArrayList<>();
        for (String word : wordsArr) {
            words.add(word);
        }
        words.add(result);

        int totalRows = words.size();

        // Find the longest string in the list and set 'totalCols' with the size of that string.
        int totalCols = 0;
        for (String word : words) {
            if (totalCols < word.length()) {
                totalCols = word.length();
            }
        }

        // Create a HashMap for the letter to digit mapping.
        HashMap<Character, Integer> letToDig = new HashMap<>();

        // Create a char array for the digit to letter mapping.
        char[] digToLet = new char[10];
        for (int i = 0; i < 10; i++) {
            digToLet[i] = '-';
        }

        return isAnyMapping(words, 0, 0, 0, letToDig, digToLet, totalRows, totalCols);
    }
}
