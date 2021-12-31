# [1423. Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards)

[中文文档](/solution/1400-1499/1423.Maximum%20Points%20You%20Can%20Obtain%20from%20Cards/README.md)

## Description

<p>There are several cards&nbsp;<strong>arranged in a row</strong>, and each card has an associated number of points&nbsp;The points are given in the integer array&nbsp;<code>cardPoints</code>.</p>

<p>In one step, you can take one card from the beginning or from the end of the row. You have to take exactly <code>k</code> cards.</p>

<p>Your score is the sum of the points of the cards you have taken.</p>

<p>Given the integer array <code>cardPoints</code> and the integer <code>k</code>, return the <em>maximum score</em> you can obtain.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> cardPoints = [1,2,3,4,5,6,1], k = 3
<strong>Output:</strong> 12
<strong>Explanation:</strong> After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> cardPoints = [2,2,2], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> Regardless of which two cards you take, your score will always be 4.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> cardPoints = [9,7,7,9,7,7,9], k = 7
<strong>Output:</strong> 55
<strong>Explanation:</strong> You have to take all the cards. Your score is the sum of points of all cards.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> cardPoints = [1,1000,1], k = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> You cannot take the card in the middle. Your best score is 1. 
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> cardPoints = [1,79,80,1,1,1,200,1], k = 3
<strong>Output:</strong> 202
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cardPoints.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= cardPoints[i] &lt;= 10^4</code></li>
	<li><code>1 &lt;= k &lt;= cardPoints.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        n = len(cardPoints)
        s = [0] * (n + 1)
        for i in range(n):
            s[i + 1] = s[i] + cardPoints[i]
        mi = float('inf')
        for i in range(n):
            j = i + (n - k) - 1
            if j < n:
                mi = min(mi, s[j + 1] - s[i])
        return s[-1] - mi
```

### **Java**

```java
class Solution {

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + cardPoints[i];
        }
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            int j = i + (n - k) - 1;
            if (j < n) {
                mi = Math.min(mi, s[j + 1] - s[i]);
            }
        }
        return s[n] - mi;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    int maxScore(vector<int>& cardPoints, int k) {
        int n = cardPoints.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + cardPoints[i];
        int mi = INT_MAX;
        for (int i = 0; i < n; ++i)
        {
            int j = i + (n - k) - 1;
            if (j < n) mi = min(mi, s[j + 1] - s[i]);
        }
        return s[n] - mi;
    }
};
```

### **Go**

```go
func maxScore(cardPoints []int, k int) int {
	n := len(cardPoints)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + cardPoints[i]
	}
	mi := math.MaxInt64
	for i := 0; i < n; i++ {
		j := i + (n - k) - 1
		if j < n {
			mi = min(mi, s[j+1]-s[i])
		}
	}
	return s[n] - mi
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
