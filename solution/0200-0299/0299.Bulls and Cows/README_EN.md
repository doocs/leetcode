---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0299.Bulls%20and%20Cows/README_EN.md
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [299. Bulls and Cows](https://leetcode.com/problems/bulls-and-cows)

[中文文档](/solution/0200-0299/0299.Bulls%20and%20Cows/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We create two counters, $cnt1$ and $cnt2$, to count the occurrence of each digit in the secret number and the friend's guess respectively. At the same time, we create a variable $x$ to count the number of bulls.

Then we iterate through the secret number and the friend's guess. If the current digit is the same, we increment $x$ by one. Otherwise, we increment the count of the current digit in the secret number and the friend's guess respectively.

Finally, we iterate through each digit in $cnt1$, take the minimum count of the current digit in $cnt1$ and $cnt2$, and add this minimum value to the variable $y$.

In the end, we return the values of $x$ and $y$.

The time complexity is $O(n)$, where $n$ is the length of the secret number and the friend's guess. The space complexity is $O(|\Sigma|)$, where $|\Sigma|$ is the size of the character set. In this problem, the character set is digits, so $|\Sigma| = 10$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        cnt1, cnt2 = Counter(), Counter()
        x = 0
        for a, b in zip(secret, guess):
            if a == b:
                x += 1
            else:
                cnt1[a] += 1
                cnt2[b] += 1
        y = sum(min(cnt1[c], cnt2[c]) for c in cnt1)
        return f"{x}A{y}B"
```

#### Java

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

#### C++

```cpp
class Solution {
public:
    string getHint(string secret, string guess) {
        int x = 0, y = 0;
        int cnt1[10]{};
        int cnt2[10]{};
        for (int i = 0; i < secret.size(); ++i) {
            int a = secret[i] - '0', b = guess[i] - '0';
            if (a == b) {
                ++x;
            } else {
                ++cnt1[a];
                ++cnt2[b];
            }
        }
        for (int i = 0; i < 10; ++i) {
            y += min(cnt1[i], cnt2[i]);
        }
        return to_string(x) + "A" + to_string(y) + "B";
    }
};
```

#### Go

```go
func getHint(secret string, guess string) string {
	x, y := 0, 0
	cnt1 := [10]int{}
	cnt2 := [10]int{}
	for i, c := range secret {
		a, b := int(c-'0'), int(guess[i]-'0')
		if a == b {
			x++
		} else {
			cnt1[a]++
			cnt2[b]++
		}
	}
	for i, c := range cnt1 {
		y += min(c, cnt2[i])

	}
	return fmt.Sprintf("%dA%dB", x, y)
}
```

#### TypeScript

```ts
function getHint(secret: string, guess: string): string {
    const cnt1: number[] = Array(10).fill(0);
    const cnt2: number[] = Array(10).fill(0);
    let x: number = 0;
    for (let i = 0; i < secret.length; ++i) {
        if (secret[i] === guess[i]) {
            ++x;
        } else {
            ++cnt1[+secret[i]];
            ++cnt2[+guess[i]];
        }
    }
    let y: number = 0;
    for (let i = 0; i < 10; ++i) {
        y += Math.min(cnt1[i], cnt2[i]);
    }
    return `${x}A${y}B`;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $secret
     * @param String $guess
     * @return String
     */
    function getHint($secret, $guess) {
        $cnt1 = array_fill(0, 10, 0);
        $cnt2 = array_fill(0, 10, 0);
        $x = 0;
        for ($i = 0; $i < strlen($secret); ++$i) {
            if ($secret[$i] === $guess[$i]) {
                ++$x;
            } else {
                ++$cnt1[(int) $secret[$i]];
                ++$cnt2[(int) $guess[$i]];
            }
        }
        $y = 0;
        for ($i = 0; $i < 10; ++$i) {
            $y += min($cnt1[$i], $cnt2[$i]);
        }
        return "{$x}A{$y}B";
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
