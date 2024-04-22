# [2137. Pour Water Between Buckets to Make Water Levels Equal 🔒](https://leetcode.com/problems/pour-water-between-buckets-to-make-water-levels-equal)

[中文文档](/solution/2100-2199/2137.Pour%20Water%20Between%20Buckets%20to%20Make%20Water%20Levels%20Equal/README.md)

<!-- tags:Array,Binary Search -->

## Description

<p>You have <code>n</code> buckets each containing some gallons of water in it, represented by a <strong>0-indexed</strong> integer array <code>buckets</code>, where the <code>i<sup>th</sup></code> bucket contains <code>buckets[i]</code> gallons of water. You are also given an integer <code>loss</code>.</p>

<p>You want to make the amount of water in each bucket equal. You can pour any amount of water from one bucket to another bucket (not necessarily an integer). However, every time you pour <code>k</code> gallons of water, you spill <code>loss</code> <strong>percent</strong> of <code>k</code>.</p>

<p>Return <em>the <strong>maximum</strong> amount of water in each bucket after making the amount of water equal. </em>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> buckets = [1,2,7], loss = 80
<strong>Output:</strong> 2.00000
<strong>Explanation:</strong> Pour 5 gallons of water from buckets[2] to buckets[0].
5 * 80% = 4 gallons are spilled and buckets[0] only receives 5 - 4 = 1 gallon of water.
All buckets have 2 gallons of water in them so return 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> buckets = [2,4,6], loss = 50
<strong>Output:</strong> 3.50000
<strong>Explanation:</strong> Pour 0.5 gallons of water from buckets[1] to buckets[0].
0.5 * 50% = 0.25 gallons are spilled and buckets[0] only receives 0.5 - 0.25 = 0.25 gallons of water.
Now, buckets = [2.25, 3.5, 6].
Pour 2.5 gallons of water from buckets[2] to buckets[0].
2.5 * 50% = 1.25 gallons are spilled and buckets[0] only receives 2.5 - 1.25 = 1.25 gallons of water.
All buckets have 3.5 gallons of water in them so return 3.5.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> buckets = [3,3,3,3], loss = 40
<strong>Output:</strong> 3.00000
<strong>Explanation:</strong> All buckets already have the same amount of water in them.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= buckets.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= buckets[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= loss &lt;= 99</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def equalizeWater(self, buckets: List[int], loss: int) -> float:
        def check(v):
            a = b = 0
            for x in buckets:
                if x >= v:
                    a += x - v
                else:
                    b += (v - x) * 100 / (100 - loss)
            return a >= b

        l, r = 0, max(buckets)
        while r - l > 1e-5:
            mid = (l + r) / 2
            if check(mid):
                l = mid
            else:
                r = mid
        return l
```

```java
class Solution {
    public double equalizeWater(int[] buckets, int loss) {
        double l = 0, r = Arrays.stream(buckets).max().getAsInt();
        while (r - l > 1e-5) {
            double mid = (l + r) / 2;
            if (check(buckets, loss, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private boolean check(int[] buckets, int loss, double v) {
        double a = 0;
        double b = 0;
        for (int x : buckets) {
            if (x > v) {
                a += x - v;
            } else {
                b += (v - x) * 100 / (100 - loss);
            }
        }
        return a >= b;
    }
}
```

```cpp
class Solution {
public:
    double equalizeWater(vector<int>& buckets, int loss) {
        double l = 0, r = *max_element(buckets.begin(), buckets.end());
        auto check = [&](double v) {
            double a = 0, b = 0;
            for (int x : buckets) {
                if (x > v) {
                    a += x - v;
                } else {
                    b += (v - x) * 100 / (100 - loss);
                }
            }
            return a >= b;
        };
        while (r - l > 1e-5) {
            double mid = (l + r) / 2;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }
};
```

```go
func equalizeWater(buckets []int, loss int) float64 {
	check := func(v float64) bool {
		var a, b float64
		for _, x := range buckets {
			if float64(x) >= v {
				a += float64(x) - v
			} else {
				b += (v - float64(x)) * 100 / float64(100-loss)
			}
		}
		return a >= b
	}

	l, r := float64(0), float64(slices.Max(buckets))
	for r-l > 1e-5 {
		mid := (l + r) / 2
		if check(mid) {
			l = mid
		} else {
			r = mid
		}
	}
	return l
}
```

```ts
function equalizeWater(buckets: number[], loss: number): number {
    let l = 0;
    let r = Math.max(...buckets);
    const check = (v: number): boolean => {
        let [a, b] = [0, 0];
        for (const x of buckets) {
            if (x >= v) {
                a += x - v;
            } else {
                b += ((v - x) * 100) / (100 - loss);
            }
        }
        return a >= b;
    };
    while (r - l > 1e-5) {
        const mid = (l + r) / 2;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- end -->
