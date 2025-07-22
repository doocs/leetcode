public class Solution {
    public bool IsValid(string word) {
        if (word.Length < 3) {
            return false;
        }

        bool hasVowel = false, hasConsonant = false;
        bool[] vs = new bool[26];
        foreach (char c in "aeiou") {
            vs[c - 'a'] = true;
        }

        foreach (char c in word) {
            if (char.IsLetter(c)) {
                char lower = char.ToLower(c);
                if (vs[lower - 'a']) {
                    hasVowel = true;
                } else {
                    hasConsonant = true;
                }
            } else if (!char.IsDigit(c)) {
                return false;
            }
        }

        return hasVowel && hasConsonant;
    }
}
