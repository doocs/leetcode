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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> secret = &quot;1807&quot;, guess = &quot;7810&quot;
<strong>Output:</strong> &quot;1A3B&quot;
<strong>Explanation:</strong> Bulls are connected with a &#39;|&#39; and cows are underlined:
&quot;1807&quot;
  |
&quot;<u>7</u>8<u>10</u>&quot;</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> secret = &quot;1123&quot;, guess = &quot;0111&quot;
<strong>Output:</strong> &quot;1A1B&quot;
<strong>Explanation:</strong> Bulls are connected with a &#39;|&#39; and cows are underlined:
&quot;1123&quot;        &quot;1123&quot;
  |      or     |
&quot;01<u>1</u>1&quot;        &quot;011<u>1</u>&quot;
Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
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
        x = y = 0
        cnt1 = [0] * 10
        cnt2 = [0] * 10
        for i in range(len(secret)):
            if secret[i] == guess[i]:
                x += 1
            else:
                cnt1[int(secret[i])] += 1
                cnt2[int(guess[i])] += 1

        for i in range(10):
            y += min(cnt1[i], cnt2[i])
        return f'{x}A{y}B'
```

### **Java**

```java
class Solution {
    public String getHint(String secret, String guess) {
        int x = 0, y = 0;
        int[] cnt1 = new int[10];
        int[] cnt2 = new int[10];
        for (int i = 0; i < secret.length(); ++i) {
            int a = secret.charAt(i) - '0', b = guess.charAt(i) - '0';
            if (a == b) {
                ++x;
            } else {
                ++cnt1[a];
                ++cnt2[b];
            }
        }
        for (int i = 0; i < 10; ++i) {
            y += Math.min(cnt1[i], cnt2[i]);
        }
        return String.format("%dA%dB", x, y);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string getHint(string secret, string guess) {
        int x = 0, y = 0;
        vector<int> cnt1(10);
        vector<int> cnt2(10);
        for (int i = 0; i < secret.size(); ++i) {
            int a = secret[i] - '0', b = guess[i] - '0';
            if (a == b)
                ++x;
            else {
                ++cnt1[a];
                ++cnt2[b];
            }
        }
        for (int i = 0; i < 10; ++i) y += min(cnt1[i], cnt2[i]);
        return to_string(x) + "A" + to_string(y) + "B";
    }
};
```

### **Go**

```go
func getHint(secret string, guess string) string {
	x, y := 0, 0
	cnt1 := make([]int, 10)
	cnt2 := make([]int, 10)
	for i := 0; i < len(secret); i++ {
		a, b := secret[i]-'0', guess[i]-'0'
		if a == b {
			x++
		} else {
			cnt1[a]++
			cnt2[b]++
		}
	}
	for i := 0; i < 10; i++ {
		y += min(cnt1[i], cnt2[i])
	}
	return fmt.Sprintf("%dA%dB", x, y)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $secret
     * @param String $guess
     * @return String
     */
    function getHint($secret, $guess) {
        $cntA = 0;
        $cntB = 0;
        $len = strlen($secret);
        for ($i = 0; $i < $len; $i++) {
            if ($secret[$i] == $guess[$i]) $cntA++;
            else $hashtable[$secret[$i]] += 1;
        }
        for ($i = 0; $i < $len; $i++) {
            if ($secret[$i] != $guess[$i] && $hashtable[$guess[$i]] > 0) {
                $cntB++;
                $hashtable[$guess[$i]] -= 1;
            }
        }
        return $cntA."A".$cntB."B";
    }
}
```

### **...**

```

```

<!-- tabs:end -->
