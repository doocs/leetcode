# [299. Bulls and Cows](https://leetcode.com/problems/bulls-and-cows)

[中文文档](/solution/0200-0299/0299.Bulls%20and%20Cows/README.md)

## Description

<p>You are playing the following <a href="https://en.wikipedia.org/wiki/Bulls_and_Cows" target="_blank">Bulls and Cows</a> game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called &quot;bulls&quot;) and how many digits match the secret number but locate in the wrong position (called &quot;cows&quot;). Your friend will use successive guesses and hints to eventually derive the secret number.</p>

<p>Write a function to return a hint according to the secret number and friend&#39;s guess, use <code>A</code> to indicate the bulls and <code>B</code> to indicate the cows.&nbsp;</p>

<p>Please note that both secret number and friend&#39;s guess may contain duplicate digits.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> secret = &quot;1807&quot;, guess = &quot;7810&quot;



<strong>Output:</strong> &quot;1A3B&quot;



<strong>Explanation:</strong> <code>1</code><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;"> bull and </span><code>3</code><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;"> cows. The bull is </span><code>8</code><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">, the cows are </span><code>0</code><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">, </span><code>1</code><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;"> and </span><code>7<font face="sans-serif, Arial, Verdana, Trebuchet MS">.</font></code></pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> secret = &quot;1123&quot;, guess = &quot;0111&quot;



<strong>Output:</strong> &quot;1A1B&quot;



<strong>Explanation: </strong>The 1st <code>1 </code><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">in friend&#39;s guess is a bull, the 2nd or 3rd </span><code>1</code><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;"> is a cow</span><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">.</span></pre>

<p><strong>Note: </strong>You may assume that the secret number and your friend&#39;s guess only contain digits, and their lengths are always equal.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        a_cnt = b_cnt = 0
        nums1 = dict()
        nums2 = dict()
        for i in range(len(secret)):
            if secret[i] == guess[i]:
                a_cnt += 1
            else:
                nums1[secret[i]] = nums1.get(secret[i], 0) + 1
                nums2[guess[i]] = nums2.get(guess[i], 0) + 1
        for i, v in nums1.items():
            if i in nums2:
                b_cnt += min(v, nums2[i])
        return f'{a_cnt}A{b_cnt}B'
```

### **Java**

```java
class Solution {
    public String getHint(String secret, String guess) {
        int aCnt = 0, bCnt = 0;
        Map<Character, Integer> nums1 = new HashMap<>();
        Map<Character, Integer> nums2 = new HashMap<>();
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++aCnt;
            } else {
                nums1.put(secret.charAt(i), nums1.getOrDefault(secret.charAt(i), 0) + 1);
                nums2.put(guess.charAt(i), nums2.getOrDefault(guess.charAt(i), 0) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : nums1.entrySet()) {
            if (nums2.containsKey(entry.getKey())) {
                bCnt += Math.min(entry.getValue(), nums2.get(entry.getKey()));
            }
        }
        return String.format("%dA%dB", aCnt, bCnt);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
