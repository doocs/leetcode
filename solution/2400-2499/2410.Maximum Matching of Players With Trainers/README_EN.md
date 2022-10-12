# [2410. Maximum Matching of Players With Trainers](https://leetcode.com/problems/maximum-matching-of-players-with-trainers)

[中文文档](/solution/2400-2499/2410.Maximum%20Matching%20of%20Players%20With%20Trainers/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>players</code>, where <code>players[i]</code> represents the <strong>ability</strong> of the <code>i<sup>th</sup></code> player. You are also given a <strong>0-indexed</strong> integer array <code>trainers</code>, where <code>trainers[j]</code> represents the <strong>training capacity </strong>of the <code>j<sup>th</sup></code> trainer.</p>

<p>The <code>i<sup>th</sup></code> player can <strong>match</strong> with the <code>j<sup>th</sup></code> trainer if the player&#39;s ability is <strong>less than or equal to</strong> the trainer&#39;s training capacity. Additionally, the <code>i<sup>th</sup></code> player can be matched with at most one trainer, and the <code>j<sup>th</sup></code> trainer can be matched with at most one player.</p>

<p>Return <em>the <strong>maximum</strong> number of matchings between </em><code>players</code><em> and </em><code>trainers</code><em> that satisfy these conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> players = [4,7,9], trainers = [8,2,5,8]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
One of the ways we can form two matchings is as follows:
- players[0] can be matched with trainers[0] since 4 &lt;= 8.
- players[1] can be matched with trainers[3] since 7 &lt;= 8.
It can be proven that 2 is the maximum number of matchings that can be formed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> players = [1,1,1], trainers = [10]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The trainer can be matched with any of the 3 players.
Each player can only be matched with one trainer, so the maximum answer is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= players.length, trainers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= players[i], trainers[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        ans = j = 0
        for p in players:
            while j < len(trainers) and trainers[j] < p:
                j += 1
            if j < len(trainers):
                ans += 1
                j += 1
        return ans
```

### **Java**

```java
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int ans = 0;
        int j = 0;
        for (int p : players) {
            while (j < trainers.length && trainers[j] < p) {
                ++j;
            }
            if (j < trainers.length) {
                ++ans;
                ++j;
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
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        sort(players.begin(), players.end());
        sort(trainers.begin(), trainers.end());
        int ans = 0, j = 0;
        for (int p : players) {
            while (j < trainers.size() && trainers[j] < p) {
                ++j;
            }
            if (j < trainers.size()) {
                ++ans;
                ++j;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func matchPlayersAndTrainers(players []int, trainers []int) int {
	sort.Ints(players)
	sort.Ints(trainers)
	ans, j := 0, 0
	for _, p := range players {
		for j < len(trainers) && trainers[j] < p {
			j++
		}
		if j < len(trainers) {
			ans++
			j++
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
