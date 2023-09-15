# [2188. Minimum Time to Finish the Race](https://leetcode.com/problems/minimum-time-to-finish-the-race)

[中文文档](/solution/2100-2199/2188.Minimum%20Time%20to%20Finish%20the%20Race/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>tires</code> where <code>tires[i] = [f<sub>i</sub>, r<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> tire can finish its <code>x<sup>th</sup></code> successive lap in <code>f<sub>i</sub> * r<sub>i</sub><sup>(x-1)</sup></code> seconds.</p>

<ul>
	<li>For example, if <code>f<sub>i</sub> = 3</code> and <code>r<sub>i</sub> = 2</code>, then the tire would finish its <code>1<sup>st</sup></code> lap in <code>3</code> seconds, its <code>2<sup>nd</sup></code> lap in <code>3 * 2 = 6</code> seconds, its <code>3<sup>rd</sup></code> lap in <code>3 * 2<sup>2</sup> = 12</code> seconds, etc.</li>
</ul>

<p>You are also given an integer <code>changeTime</code> and an integer <code>numLaps</code>.</p>

<p>The race consists of <code>numLaps</code> laps and you may start the race with <strong>any</strong> tire. You have an <strong>unlimited</strong> supply of each tire and after every lap, you may <strong>change</strong> to any given tire (including the current tire type) if you wait <code>changeTime</code> seconds.</p>

<p>Return<em> the <strong>minimum</strong> time to finish the race.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
<strong>Output:</strong> 21
<strong>Explanation:</strong> 
Lap 1: Start with tire 0 and finish the lap in 2 seconds.
Lap 2: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
Lap 3: Change tires to a new tire 0 for 5 seconds and then finish the lap in another 2 seconds.
Lap 4: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
Total time = 2 + 6 + 5 + 2 + 6 = 21 seconds.
The minimum time to complete the race is 21 seconds.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
<strong>Output:</strong> 25
<strong>Explanation:</strong> 
Lap 1: Start with tire 1 and finish the lap in 2 seconds.
Lap 2: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
Lap 3: Change tires to a new tire 1 for 6 seconds and then finish the lap in another 2 seconds.
Lap 4: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
Lap 5: Change tires to tire 0 for 6 seconds then finish the lap in another 1 second.
Total time = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 seconds.
The minimum time to complete the race is 25 seconds. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tires.length &lt;= 10<sup>5</sup></code></li>
	<li><code>tires[i].length == 2</code></li>
	<li><code>1 &lt;= f<sub>i</sub>, changeTime &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= numLaps &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumFinishTime(
        self, tires: List[List[int]], changeTime: int, numLaps: int
    ) -> int:
        cost = [inf] * 18
        for f, r in tires:
            i, s, t = 1, 0, f
            while t <= changeTime + f:
                s += t
                cost[i] = min(cost[i], s)
                t *= r
                i += 1
        f = [inf] * (numLaps + 1)
        f[0] = -changeTime
        for i in range(1, numLaps + 1):
            for j in range(1, min(18, i + 1)):
                f[i] = min(f[i], f[i - j] + cost[j])
            f[i] += changeTime
        return f[numLaps]
```

### **Java**

```java
class Solution {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        final int inf = 1 << 30;
        int[] cost = new int[18];
        Arrays.fill(cost, inf);
        for (int[] e : tires) {
            int f = e[0], r = e[1];
            int s = 0, t = f;
            for (int i = 1; t <= changeTime + f; ++i) {
                s += t;
                cost[i] = Math.min(cost[i], s);
                t *= r;
            }
        }
        int[] f = new int[numLaps + 1];
        Arrays.fill(f, inf);
        f[0] = -changeTime;
        for (int i = 1; i <= numLaps; ++i) {
            for (int j = 1; j < Math.min(18, i + 1); ++j) {
                f[i] = Math.min(f[i], f[i - j] + cost[j]);
            }
            f[i] += changeTime;
        }
        return f[numLaps];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumFinishTime(vector<vector<int>>& tires, int changeTime, int numLaps) {
        int cost[18];
        memset(cost, 0x3f, sizeof(cost));
        for (auto& e : tires) {
            int f = e[0], r = e[1];
            int s = 0;
            long long t = f;
            for (int i = 1; t <= changeTime + f; ++i) {
                s += t;
                cost[i] = min(cost[i], s);
                t *= r;
            }
        }
        int f[numLaps + 1];
        memset(f, 0x3f, sizeof(f));
        f[0] = -changeTime;
        for (int i = 1; i <= numLaps; ++i) {
            for (int j = 1; j < min(18, i + 1); ++j) {
                f[i] = min(f[i], f[i - j] + cost[j]);
            }
            f[i] += changeTime;
        }
        return f[numLaps];
    }
};
```

### **Go**

```go
func minimumFinishTime(tires [][]int, changeTime int, numLaps int) int {
	const inf = 1 << 30
	cost := [18]int{}
	for i := range cost {
		cost[i] = inf
	}
	for _, e := range tires {
		f, r := e[0], e[1]
		s, t := 0, f
		for i := 1; t <= changeTime+f; i++ {
			s += t
			cost[i] = min(cost[i], s)
			t *= r
		}
	}
	f := make([]int, numLaps+1)
	for i := range f {
		f[i] = inf
	}
	f[0] = -changeTime
	for i := 1; i <= numLaps; i++ {
		for j := 1; j < min(18, i+1); j++ {
			f[i] = min(f[i], f[i-j]+cost[j])
		}
		f[i] += changeTime
	}
	return f[numLaps]
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
function minimumFinishTime(tires: number[][], changeTime: number, numLaps: number): number {
    const inf = 1 << 30;
    const cost: number[] = Array(18).fill(inf);
    for (const [f, r] of tires) {
        let s = 0;
        let t = f;
        for (let i = 1; t <= changeTime + f; ++i) {
            s += t;
            cost[i] = Math.min(cost[i], s);
            t *= r;
        }
    }
    const f: number[] = Array(numLaps + 1).fill(inf);
    f[0] = -changeTime;
    for (let i = 1; i <= numLaps; ++i) {
        for (let j = 1; j < Math.min(18, i + 1); ++j) {
            f[i] = Math.min(f[i], f[i - j] + cost[j]);
        }
        f[i] += changeTime;
    }
    return f[numLaps];
}
```

### **...**

```

```

<!-- tabs:end -->
