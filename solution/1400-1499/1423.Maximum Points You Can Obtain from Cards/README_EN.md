# [1423. Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards)

[中文文档](/solution/1400-1499/1423.Maximum%20Points%20You%20Can%20Obtain%20from%20Cards/README.md)

## Description

<p>There are several cards <strong>arranged in a row</strong>, and each card has an associated number of points. The points are given in the integer array <code>cardPoints</code>.</p>

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

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cardPoints.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= cardPoints[i] &lt;= 10<sup>4</sup></code></li>
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
        mi = inf
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
        for (int i = 0; i < n; ++i) {
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

### **TypeScript**

```ts
function maxScore(cardPoints: number[], k: number): number {
    const n = cardPoints.length;
    let sum = cardPoints.slice(0, n - k).reduce((r, v) => r + v, 0);
    let min = sum;
    for (let i = 0; i < k; i++) {
        sum += cardPoints[n - k + i] - cardPoints[i];
        min = Math.min(min, sum);
    }
    return cardPoints.reduce((r, v) => r + v, 0) - min;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_score(card_points: Vec<i32>, k: i32) -> i32 {
        let (k, n) = (k as usize, card_points.len());
        let mut sum = card_points.iter().take(n - k).sum::<i32>();
        let mut min = sum;
        for i in 0..k {
            sum += card_points[n - k + i] - card_points[i];
            min = min.min(sum);
        }
        card_points.iter().sum::<i32>() - min
    }
}
```

### **...**

```

```

<!-- tabs:end -->
