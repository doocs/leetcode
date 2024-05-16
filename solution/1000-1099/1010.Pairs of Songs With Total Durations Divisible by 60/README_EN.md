---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1010.Pairs%20of%20Songs%20With%20Total%20Durations%20Divisible%20by%2060/README_EN.md
rating: 1377
source: Weekly Contest 128 Q2
tags:
    - Array
    - Hash Table
    - Counting
---

# [1010. Pairs of Songs With Total Durations Divisible by 60](https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60)

[中文文档](/solution/1000-1099/1010.Pairs%20of%20Songs%20With%20Total%20Durations%20Divisible%20by%2060/README.md)

## Description

<p>You are given a list of songs where the <code>i<sup>th</sup></code> song has a duration of <code>time[i]</code> seconds.</p>

<p>Return <em>the number of pairs of songs for which their total duration in seconds is divisible by</em> <code>60</code>. Formally, we want the number of indices <code>i</code>, <code>j</code> such that <code>i &lt; j</code> with <code>(time[i] + time[j]) % 60 == 0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = [30,20,150,100,40]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = [60,60,60]
<strong>Output:</strong> 3
<strong>Explanation:</strong> All three pairs have a total duration of 120, which is divisible by 60.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 6 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= time[i] &lt;= 500</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        cnt = Counter(t % 60 for t in time)
        ans = sum(cnt[x] * cnt[60 - x] for x in range(1, 30))
        ans += cnt[0] * (cnt[0] - 1) // 2
        ans += cnt[30] * (cnt[30] - 1) // 2
        return ans
```

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] cnt = new int[60];
        for (int t : time) {
            ++cnt[t % 60];
        }
        int ans = 0;
        for (int x = 1; x < 30; ++x) {
            ans += cnt[x] * cnt[60 - x];
        }
        ans += (long) cnt[0] * (cnt[0] - 1) / 2;
        ans += (long) cnt[30] * (cnt[30] - 1) / 2;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        int cnt[60]{};
        for (int& t : time) {
            ++cnt[t % 60];
        }
        int ans = 0;
        for (int x = 1; x < 30; ++x) {
            ans += cnt[x] * cnt[60 - x];
        }
        ans += 1LL * cnt[0] * (cnt[0] - 1) / 2;
        ans += 1LL * cnt[30] * (cnt[30] - 1) / 2;
        return ans;
    }
};
```

```go
func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [60]int{}
	for _, t := range time {
		cnt[t%60]++
	}
	for x := 1; x < 30; x++ {
		ans += cnt[x] * cnt[60-x]
	}
	ans += cnt[0] * (cnt[0] - 1) / 2
	ans += cnt[30] * (cnt[30] - 1) / 2
	return
}
```

```ts
function numPairsDivisibleBy60(time: number[]): number {
    const cnt: number[] = new Array(60).fill(0);
    for (const t of time) {
        ++cnt[t % 60];
    }
    let ans = 0;
    for (let x = 1; x < 30; ++x) {
        ans += cnt[x] * cnt[60 - x];
    }
    ans += (cnt[0] * (cnt[0] - 1)) / 2;
    ans += (cnt[30] * (cnt[30] - 1)) / 2;
    return ans;
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        cnt = Counter()
        ans = 0
        for x in time:
            x %= 60
            y = (60 - x) % 60
            ans += cnt[y]
            cnt[x] += 1
        return ans
```

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] cnt = new int[60];
        int ans = 0;
        for (int x : time) {
            x %= 60;
            int y = (60 - x) % 60;
            ans += cnt[y];
            ++cnt[x];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        int cnt[60]{};
        int ans = 0;
        for (int x : time) {
            x %= 60;
            int y = (60 - x) % 60;
            ans += cnt[y];
            ++cnt[x];
        }
        return ans;
    }
};
```

```go
func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [60]int{}
	for _, x := range time {
		x %= 60
		y := (60 - x) % 60
		ans += cnt[y]
		cnt[x]++
	}
	return
}
```

```ts
function numPairsDivisibleBy60(time: number[]): number {
    const cnt: number[] = new Array(60).fill(0);
    let ans: number = 0;
    for (let x of time) {
        x %= 60;
        const y = (60 - x) % 60;
        ans += cnt[y];
        ++cnt[x];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
