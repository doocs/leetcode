# [299. Bulls and Cows](https://leetcode.com/problems/bulls-and-cows)

[中文文档](/solution/0200-0299/0299.Bulls%20and%20Cows/README.md)

## Description

<p>You are playing the <strong><a href="https://en.wikipedia.org/wiki/Bulls_and_Cows" target="_blank">Bulls and Cows</a></strong> game with your friend.</p>

<p>You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:</p>

<ul>
	<li>The number of &quot;bulls&quot;, which are digits in the guess that are in the correct position.</li>
	<li>The number of &quot;cows&quot;, which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.</li>
</ul>

<p>Given the secret number <code>secret</code> and your friend&#39;s guess <code>guess</code>, return <em>the hint for your friend&#39;s guess</em>.</p>

<p>The hint should be formatted as <code>&quot;xAyB&quot;</code>, where <code>x</code> is the number of bulls and <code>y</code> is the number of cows. Note that both <code>secret</code> and <code>guess</code> may contain duplicate digits.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> secret = &quot;1807&quot;, guess = &quot;7810&quot;
<strong>Output:</strong> &quot;1A3B&quot;
<strong>Explanation:</strong> Bulls are connected with a &#39;|&#39; and cows are underlined:
&quot;1807&quot;
  |
&quot;<u>7</u>8<u>10</u>&quot;</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> secret = &quot;1123&quot;, guess = &quot;0111&quot;
<strong>Output:</strong> &quot;1A1B&quot;
<strong>Explanation:</strong> Bulls are connected with a &#39;|&#39; and cows are underlined:
&quot;1123&quot;        &quot;1123&quot;
  |      or     |
&quot;01<u>1</u>1&quot;        &quot;011<u>1</u>&quot;
Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> secret = &quot;1&quot;, guess = &quot;0&quot;
<strong>Output:</strong> &quot;0A0B&quot;
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> secret = &quot;1&quot;, guess = &quot;1&quot;
<strong>Output:</strong> &quot;1A0B&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= secret.length, guess.length &lt;= 1000</code></li>
	<li><code>secret.length == guess.length</code></li>
	<li><code>secret</code> and <code>guess</code> consist of digits only.</li>
</ul>


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
