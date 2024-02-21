# [2437. Number of Valid Clock Times](https://leetcode.com/problems/number-of-valid-clock-times)

[中文文档](/solution/2400-2499/2437.Number%20of%20Valid%20Clock%20Times/README.md)

<!-- tags:String,Enumeration -->

## Description

<p>You are given a string of length <code>5</code> called <code>time</code>, representing the current time on a digital clock in the format <code>&quot;hh:mm&quot;</code>. The <strong>earliest</strong> possible time is <code>&quot;00:00&quot;</code> and the <strong>latest</strong> possible time is <code>&quot;23:59&quot;</code>.</p>

<p>In the string <code>time</code>, the digits represented by the <code>?</code>&nbsp;symbol are <strong>unknown</strong>, and must be <strong>replaced</strong> with a digit from <code>0</code> to <code>9</code>.</p>

<p>Return<em> an integer </em><code>answer</code><em>, the number of valid clock times that can be created by replacing every </em><code>?</code><em>&nbsp;with a digit from </em><code>0</code><em> to </em><code>9</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;?5:00&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can replace the ? with either a 0 or 1, producing &quot;05:00&quot; or &quot;15:00&quot;. Note that we cannot replace it with a 2, since the time &quot;25:00&quot; is invalid. In total, we have two choices.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;0?:0?&quot;
<strong>Output:</strong> 100
<strong>Explanation:</strong> Each ? can be replaced by any digit from 0 to 9, so we have 100 total choices.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;??:??&quot;
<strong>Output:</strong> 1440
<strong>Explanation:</strong> There are 24 possible choices for the hours, and 60 possible choices for the minutes. In total, we have 24 * 60 = 1440 choices.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>time</code> is a valid string of length <code>5</code> in the format <code>&quot;hh:mm&quot;</code>.</li>
	<li><code>&quot;00&quot; &lt;= hh &lt;= &quot;23&quot;</code></li>
	<li><code>&quot;00&quot; &lt;= mm &lt;= &quot;59&quot;</code></li>
	<li>Some of the digits might be replaced with <code>&#39;?&#39;</code> and need to be replaced with digits from <code>0</code> to <code>9</code>.</li>
</ul>

## Solutions

### Solution 1: Enumeration

We can directly enumerate all times from $00:00$ to $23:59$, then judge whether each time is valid, if so, increment the answer.

After the enumeration ends, return the answer.

The time complexity is $O(24 \times 60)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def countTime(self, time: str) -> int:
        def check(s: str, t: str) -> bool:
            return all(a == b or b == '?' for a, b in zip(s, t))

        return sum(
            check(f'{h:02d}:{m:02d}', time) for h in range(24) for m in range(60)
        )
```

```java
class Solution {
    public int countTime(String time) {
        int ans = 0;
        for (int h = 0; h < 24; ++h) {
            for (int m = 0; m < 60; ++m) {
                String s = String.format("%02d:%02d", h, m);
                int ok = 1;
                for (int i = 0; i < 5; ++i) {
                    if (s.charAt(i) != time.charAt(i) && time.charAt(i) != '?') {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countTime(string time) {
        int ans = 0;
        for (int h = 0; h < 24; ++h) {
            for (int m = 0; m < 60; ++m) {
                char s[20];
                sprintf(s, "%02d:%02d", h, m);
                int ok = 1;
                for (int i = 0; i < 5; ++i) {
                    if (s[i] != time[i] && time[i] != '?') {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
            }
        }
        return ans;
    }
};
```

```go
func countTime(time string) int {
	ans := 0
	for h := 0; h < 24; h++ {
		for m := 0; m < 60; m++ {
			s := fmt.Sprintf("%02d:%02d", h, m)
			ok := 1
			for i := 0; i < 5; i++ {
				if s[i] != time[i] && time[i] != '?' {
					ok = 0
					break
				}
			}
			ans += ok
		}
	}
	return ans
}
```

```ts
function countTime(time: string): number {
    let ans = 0;
    for (let h = 0; h < 24; ++h) {
        for (let m = 0; m < 60; ++m) {
            const s = `${h}`.padStart(2, '0') + ':' + `${m}`.padStart(2, '0');
            let ok = 1;
            for (let i = 0; i < 5; ++i) {
                if (s[i] !== time[i] && time[i] !== '?') {
                    ok = 0;
                    break;
                }
            }
            ans += ok;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn count_time(time: String) -> i32 {
        let mut ans = 0;

        for i in 0..24 {
            for j in 0..60 {
                let mut ok = true;
                let t = format!("{:02}:{:02}", i, j);

                for (k, ch) in time.chars().enumerate() {
                    if ch != '?' && ch != t.chars().nth(k).unwrap() {
                        ok = false;
                    }
                }

                if ok {
                    ans += 1;
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

### Solution 2: Optimized Enumeration

We can separately enumerate hours and minutes, count how many hours and minutes meet the condition, and then multiply them together.

The time complexity is $O(24 + 60)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def countTime(self, time: str) -> int:
        def f(s: str, m: int) -> int:
            cnt = 0
            for i in range(m):
                a = s[0] == '?' or (int(s[0]) == i // 10)
                b = s[1] == '?' or (int(s[1]) == i % 10)
                cnt += a and b
            return cnt

        return f(time[:2], 24) * f(time[3:], 60)
```

```java
class Solution {
    public int countTime(String time) {
        return f(time.substring(0, 2), 24) * f(time.substring(3), 60);
    }

    private int f(String s, int m) {
        int cnt = 0;
        for (int i = 0; i < m; ++i) {
            boolean a = s.charAt(0) == '?' || s.charAt(0) - '0' == i / 10;
            boolean b = s.charAt(1) == '?' || s.charAt(1) - '0' == i % 10;
            cnt += a && b ? 1 : 0;
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int countTime(string time) {
        auto f = [](string s, int m) {
            int cnt = 0;
            for (int i = 0; i < m; ++i) {
                bool a = s[0] == '?' || s[0] - '0' == i / 10;
                bool b = s[1] == '?' || s[1] - '0' == i % 10;
                cnt += a && b;
            }
            return cnt;
        };
        return f(time.substr(0, 2), 24) * f(time.substr(3, 2), 60);
    }
};
```

```go
func countTime(time string) int {
	f := func(s string, m int) (cnt int) {
		for i := 0; i < m; i++ {
			a := s[0] == '?' || int(s[0]-'0') == i/10
			b := s[1] == '?' || int(s[1]-'0') == i%10
			if a && b {
				cnt++
			}
		}
		return
	}
	return f(time[:2], 24) * f(time[3:], 60)
}
```

```ts
function countTime(time: string): number {
    const f = (s: string, m: number): number => {
        let cnt = 0;
        for (let i = 0; i < m; ++i) {
            const a = s[0] === '?' || s[0] === Math.floor(i / 10).toString();
            const b = s[1] === '?' || s[1] === (i % 10).toString();
            if (a && b) {
                ++cnt;
            }
        }
        return cnt;
    };
    return f(time.slice(0, 2), 24) * f(time.slice(3), 60);
}
```

```rust
impl Solution {
    pub fn count_time(time: String) -> i32 {
        let f = |s: &str, m: usize| -> i32 {
            let mut cnt = 0;
            let first = s.chars().nth(0).unwrap();
            let second = s.chars().nth(1).unwrap();

            for i in 0..m {
                let a = first == '?' || (first.to_digit(10).unwrap() as usize) == i / 10;

                let b = second == '?' || (second.to_digit(10).unwrap() as usize) == i % 10;

                if a && b {
                    cnt += 1;
                }
            }

            cnt
        };

        f(&time[..2], 24) * f(&time[3..], 60)
    }
}
```

<!-- tabs:end -->

<!-- end -->
