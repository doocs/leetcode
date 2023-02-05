class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        List<Integer> t = new ArrayList<>();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        for (int i = 0; i < words.length; ++i) {
            char a = words[i].charAt(0), b = words[i].charAt(words[i].length() - 1);
            if (vowels.contains(a) && vowels.contains(b)) {
                t.add(i);
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = search(t, queries[i][1] + 1) - search(t, queries[i][0]);
        }
        return ans;
    }

    private int search(List<Integer> nums, int x) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}