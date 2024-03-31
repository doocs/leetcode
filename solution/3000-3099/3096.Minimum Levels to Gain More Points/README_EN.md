# [3096. Minimum Levels to Gain More Points](https://leetcode.com/problems/minimum-levels-to-gain-more-points)

[中文文档](/solution/3000-3099/3096.Minimum%20Levels%20to%20Gain%20More%20Points/README.md)

<!-- tags: -->

## Description

<p>You are given a binary array <code>possible</code> of length <code>n</code>.</p>

<p>Danielchandg and Bob are playing a game that consists of <code>n</code> levels. Some of the levels in the game are <strong>impossible</strong> to clear while others can <strong>always</strong> be cleared. In particular, if <code>possible[i] == 0</code>, then the <code>i<sup>th</sup></code> level is <strong>impossible</strong> to clear for <strong>both</strong> the players. A player gains <code>1</code> point on clearing a level and loses <code>1</code> point if the player fails to clear it.</p>

<p>At the start of the game, Danielchandg will play some levels in the <strong>given order</strong> starting from the <code>0<sup>th</sup></code> level, after which Bob will play for the rest of the levels.</p>

<p>Danielchandg wants to know the <strong>minimum</strong> number of levels he should play to gain more points than Bob, if both players play optimally to <strong>maximize</strong> their points.</p>

<p>Return <em>the <strong>minimum</strong> number of levels danielchandg should play to gain more points</em>. <em>If this is <strong>not</strong> possible, return</em> <code>-1</code>.</p>

<p><strong>Note</strong> that each player must play at least <code>1</code> level.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">possible = [1,0,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s look at all the levels that Danielchandg can play up to:</p>

<ul>
	<li>If Danielchandg plays only level 0 and Bob plays the rest of the levels, Danielchandg has 1 point, while Bob has -1 + 1 - 1 = -1 point.</li>
	<li>If Danielchandg plays till level 1 and Bob plays the rest of the levels, Danielchandg has 1 - 1 = 0 points, while Bob has 1 - 1 = 0 points.</li>
	<li>If Danielchandg plays till level 2 and Bob plays the rest of the levels, Danielchandg has 1 - 1 + 1 = 1 point, while Bob has -1 point.</li>
</ul>

<p>Danielchandg must play a minimum of 1 level to gain more points.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">possible = [1,1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s look at all the levels that Danielchandg can play up to:</p>

<ul>
	<li>If Danielchandg plays only level 0 and Bob plays the rest of the levels, Danielchandg has 1 point, while Bob has 4 points.</li>
	<li>If Danielchandg plays till level 1 and Bob plays the rest of the levels, Danielchandg has 2 points, while Bob has 3 points.</li>
	<li>If Danielchandg plays till level 2 and Bob plays the rest of the levels, Danielchandg has 3 points, while Bob has 2 points.</li>
	<li>If Danielchandg plays till level 3 and Bob plays the rest of the levels, Danielchandg has 4 points, while Bob has 1 point.</li>
</ul>

<p>Danielchandg must play a minimum of 3 levels to gain more points.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">possible = [0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible way is for both players to play 1 level each. Danielchandg plays level 0 and loses 1 point. Bob plays level 1 and loses 1 point. As both players have equal points, Danielchandg can&#39;t gain more points than Bob.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == possible.length &lt;= 10<sup>5</sup></code></li>
	<li><code>possible[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minimumLevels(self, possible: List[int]) -> int:
        s = sum(-1 if x == 0 else 1 for x in possible)
        t = 0
        for i, x in enumerate(possible[:-1], 1):
            t += -1 if x == 0 else 1
            if t > s - t:
                return i
        return -1
```

```java
class Solution {
    public int minimumLevels(int[] possible) {
        int s = 0;
        for (int x : possible) {
            s += x == 0 ? -1 : 1;
        }
        int t = 0;
        for (int i = 1; i < possible.length; ++i) {
            t += possible[i - 1] == 0 ? -1 : 1;
            if (t > s - t) {
                return i;
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int minimumLevels(vector<int>& possible) {
        int s = 0;
        for (int x : possible) {
            s += x == 0 ? -1 : 1;
        }
        int t = 0;
        for (int i = 1; i < possible.size(); ++i) {
            t += possible[i - 1] == 0 ? -1 : 1;
            if (t > s - t) {
                return i;
            }
        }
        return -1;
    }
};
```

```go
func minimumLevels(possible []int) int {
	s := 0
	for _, x := range possible {
		if x == 0 {
			x = -1
		}
		s += x
	}
	t := 0
	for i, x := range possible[:len(possible)-1] {
		if x == 0 {
			x = -1
		}
		t += x
		if t > s-t {
			return i + 1
		}
	}
	return -1
}
```

```ts
function minimumLevels(possible: number[]): number {
    const s = possible.reduce((acc, x) => acc + (x === 0 ? -1 : 1), 0);
    let t = 0;
    for (let i = 1; i < possible.length; ++i) {
        t += possible[i - 1] === 0 ? -1 : 1;
        if (t > s - t) {
            return i;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- end -->
