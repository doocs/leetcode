# [948. Bag of Tokens](https://leetcode.com/problems/bag-of-tokens)

[中文文档](/solution/0900-0999/0948.Bag%20of%20Tokens/README.md)

## Description

<p>You have an initial <strong>power</strong> of <code>power</code>, an initial <strong>score</strong> of <code>0</code>, and a bag of <code>tokens</code> where <code>tokens[i]</code> is the value of the <code>i<sup>th</sup></code> token (0-indexed).</p>

<p>Your goal is to maximize your total <strong>score</strong> by potentially playing each token in one of two ways:</p>

<ul>
	<li>If your current <strong>power</strong> is at least <code>tokens[i]</code>, you may play the <code>i<sup>th</sup></code> token face up, losing <code>tokens[i]</code> <strong>power</strong> and gaining <code>1</code> <strong>score</strong>.</li>
	<li>If your current <strong>score</strong> is at least <code>1</code>, you may play the <code>i<sup>th</sup></code> token face down, gaining <code>tokens[i]</code> <strong>power</strong> and losing <code>1</code> <strong>score</strong>.</li>
</ul>

<p>Each token may be played <strong>at most</strong> once and <strong>in any order</strong>. You do <strong>not</strong> have to play all the tokens.</p>

<p>Return <em>the largest possible <strong>score</strong> you can achieve after playing any number of tokens</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tokens = [100], power = 50
<strong>Output:</strong> 0
<strong>Explanation</strong><strong>:</strong> Playing the only token in the bag is impossible because you either have too little power or too little score.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tokens = [100,200], power = 150
<strong>Output:</strong> 1
<strong>Explanation:</strong> Play the 0<sup>th</sup> token (100) face up, your power becomes 50 and score becomes 1.
There is no need to play the 1<sup>st</sup> token since you cannot play it face up to add to your score.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> tokens = [100,200,300,400], power = 200
<strong>Output:</strong> 2
<strong>Explanation:</strong> Play the tokens in this order to get a score of 2:
1. Play the 0<sup>th</sup> token (100) face up, your power becomes 100 and score becomes 1.
2. Play the 3<sup>rd</sup> token (400) face down, your power becomes 500 and score becomes 0.
3. Play the 1<sup>st</sup> token (200) face up, your power becomes 300 and score becomes 1.
4. Play the 2<sup>nd </sup>token (300) face up, your power becomes 0 and score becomes 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= tokens.length &lt;= 1000</code></li>
	<li><code>0 &lt;= tokens[i],&nbsp;power &lt; 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def bagOfTokensScore(self, tokens: List[int], power: int) -> int:
        tokens.sort()
        i, j = 0, len(tokens) - 1
        ans = t = 0
        while i <= j:
            if power >= tokens[i]:
                power -= tokens[i]
                i, t = i + 1, t + 1
                ans = max(ans, t)
            elif t:
                power += tokens[j]
                j, t = j - 1, t - 1
            else:
                break
        return ans
```

### **Java**

```java
class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int i = 0, j = tokens.length - 1;
        int ans = 0, t = 0;
        while (i <= j) {
            if (power >= tokens[i]) {
                power -= tokens[i++];
                ++t;
                ans = Math.max(ans, t);
            } else if (t > 0) {
                power += tokens[j--];
                --t;
            } else {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int bagOfTokensScore(vector<int>& tokens, int power) {
        sort(tokens.begin(), tokens.end());
        int i = 0, j = tokens.size() - 1;
        int ans = 0, t = 0;
        while (i <= j) {
            if (power >= tokens[i]) {
                power -= tokens[i++];
                ans = max(ans, ++t);
            } else if (t) {
                power += tokens[j--];
                --t;
            } else {
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func bagOfTokensScore(tokens []int, power int) int {
	sort.Ints(tokens)
	i, j := 0, len(tokens)-1
	ans, t := 0, 0
	for i <= j {
		if power >= tokens[i] {
			power -= tokens[i]
			i, t = i+1, t+1
			ans = max(ans, t)
		} else if t > 0 {
			power += tokens[j]
			j, t = j-1, t-1
		} else {
			break
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
