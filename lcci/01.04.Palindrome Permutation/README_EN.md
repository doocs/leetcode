# [01.04. Palindrome Permutation](https://leetcode-cn.com/problems/palindrome-permutation-lcci)

## Description
<p>Given a string, write a function to check if it is a permutation of a palin&shy; drome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.</p>



<p>&nbsp;</p>



<p><strong>Example1: </strong></p>



<pre>

<strong>Input: &quot;</strong>tactcoa&quot;

<strong>Output: </strong>true（permutations: &quot;tacocat&quot;、&quot;atcocta&quot;, etc.）

</pre>



<p>&nbsp;</p>




## Solutions


### Python3

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        if s is None or len(s) < 2:
            return True
        cache = {}
        for ch in s:
            cache[ch] = 1 if cache.get(ch) is None else cache[ch] + 1
        cnt = 0
        for k, v in cache.items():
            if (v & 1) == 1:
                cnt += 1
            if cnt > 1:
                return False
        return cnt <= 1
```

### Java

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> counter = new HashMap<>();
        for (char ch : chars) {
            counter.put(ch, counter.get(ch) == null ? 1 : counter.get(ch) + 1);
        }
        int cnt = 0;
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            if ((entry.getValue() & 1) == 1) {
                ++cnt;
            }
            if (cnt > 1) {
                return false;
            }
        }
        return cnt <= 1;
    }
}
```

### ...
```

```
