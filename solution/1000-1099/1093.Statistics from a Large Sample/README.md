# [1093. 大样本统计](https://leetcode.cn/problems/statistics-from-a-large-sample)

[English Version](/solution/1000-1099/1093.Statistics%20from%20a%20Large%20Sample/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们对&nbsp;<code>0</code>&nbsp;到&nbsp;<code>255</code>&nbsp;之间的整数进行采样，并将结果存储在数组&nbsp;<code>count</code>&nbsp;中：<code>count[k]</code>&nbsp;就是整数&nbsp;<code>k</code> 在样本中出现的次数。</p>

<p>计算以下统计数据:</p>

<ul>
	<li><code>minimum</code>&nbsp;：样本中的最小元素。</li>
	<li><code>maximum</code>&nbsp;：样品中的最大元素。</li>
	<li><code>mean</code>&nbsp;：样本的平均值，计算为所有元素的总和除以元素总数。</li>
	<li><code>median</code>&nbsp;：
	<ul>
		<li>如果样本的元素个数是奇数，那么一旦样本排序后，中位数 <code>median</code> 就是中间的元素。</li>
		<li>如果样本中有偶数个元素，那么中位数<code>median</code> 就是样本排序后中间两个元素的平均值。</li>
	</ul>
	</li>
	<li><code>mode</code>&nbsp;：样本中出现次数最多的数字。保众数是 <strong>唯一</strong> 的。</li>
</ul>

<p>以浮点数数组的形式返回样本的统计信息<em>&nbsp;</em><code>[minimum, maximum, mean, median, mode]</code>&nbsp;。与真实答案误差在<em>&nbsp;</em><code>10<sup>-5</sup></code><em>&nbsp;</em>内的答案都可以通过。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
<strong>输出：</strong>[1.00000,3.00000,2.37500,2.50000,3.00000]
<strong>解释：</strong>用count表示的样本为[1,2,2,2,3,3,3,3]。
最小值和最大值分别为1和3。
均值是(1+2+2+2+3+3+3+3) / 8 = 19 / 8 = 2.375。
因为样本的大小是偶数，所以中位数是中间两个元素2和3的平均值，也就是2.5。
众数为3，因为它在样本中出现的次数最多。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
<strong>输出：</strong>[1.00000,4.00000,2.18182,2.00000,1.00000]
<strong>解释：</strong>用count表示的样本为[1,1,1,1,2,2,3,3,3,4,4]。
最小值为1，最大值为4。
平均数是(1+1+1+1+2+2+2+3+3+4+4)/ 11 = 24 / 11 = 2.18181818…(为了显示，输出显示了整数2.18182)。
因为样本的大小是奇数，所以中值是中间元素2。
众数为1，因为它在样本中出现的次数最多。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>count.length == 256</code></li>
	<li><code>0 &lt;= count[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= sum(count) &lt;= 10<sup>9</sup></code></li>
	<li>&nbsp;<code>count</code>&nbsp;的众数是 <strong>唯一</strong> 的</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们直接根据题目描述模拟即可，定义以下变量：

-   变量 $mi$ 表示最小值；
-   变量 $mx$ 表示最大值；
-   变量 $s$ 表示总和；
-   变量 $cnt$ 表示总个数；
-   变量 $mode$ 表示众数。

我们遍历数组 $count$，对于当前遍历到的数字 $count[k]$，如果 $count[k] \gt 0$，那么我们做以下更新操作：

-   更新 $mi = \min(mi, k)$；
-   更新 $mx = \max(mx, k)$；
-   更新 $s = s + k \times count[k]$；
-   更新 $cnt = cnt + count[k]$；
-   如果 $count[k] \gt count[mode]$，那么更新 $mode = k$。

遍历结束后，我们再根据 $cnt$ 的奇偶性来计算中位数 $median$，如果 $cnt$ 是奇数，那么中位数就是第 $\lfloor \frac{cnt}{2} \rfloor + 1$ 个数字，如果 $cnt$ 是偶数，那么中位数就是第 $\lfloor \frac{cnt}{2} \rfloor$ 和第 $\lfloor \frac{cnt}{2} \rfloor + 1$ 个数字的平均值。

> 这里我们通过一个简单的辅助函数 $find(i)$ 来找到第 $i$ 个数字，具体实现可以参考下面的代码。

最后，我们将 $mi, mx, \frac{s}{cnt}, median, mode$ 放入答案数组中返回即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $count$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sampleStats(self, count: List[int]) -> List[float]:
        def find(i: int) -> int:
            t = 0
            for k, x in enumerate(count):
                t += x
                if t >= i:
                    return k

        mi, mx = inf, -1
        s = cnt = 0
        mode = 0
        for k, x in enumerate(count):
            if x:
                mi = min(mi, k)
                mx = max(mx, k)
                s += k * x
                cnt += x
                if x > count[mode]:
                    mode = k

        median = (
            find(cnt // 2 + 1) if cnt & 1 else (find(cnt // 2) + find(cnt // 2 + 1)) / 2
        )
        return [mi, mx, s / cnt, median, mode]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] count;

    public double[] sampleStats(int[] count) {
        this.count = count;
        int mi = 1 << 30, mx = -1;
        long s = 0;
        int cnt = 0;
        int mode = 0;
        for (int k = 0; k < count.length; ++k) {
            if (count[k] > 0) {
                mi = Math.min(mi, k);
                mx = Math.max(mx, k);
                s += 1L * k * count[k];
                cnt += count[k];
                if (count[k] > count[mode]) {
                    mode = k;
                }
            }
        }
        double median
            = cnt % 2 == 1 ? find(cnt / 2 + 1) : (find(cnt / 2) + find(cnt / 2 + 1)) / 2.0;
        return new double[] {mi, mx, s * 1.0 / cnt, median, mode};
    }

    private int find(int i) {
        for (int k = 0, t = 0;; ++k) {
            t += count[k];
            if (t >= i) {
                return k;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<double> sampleStats(vector<int>& count) {
        auto find = [&](int i) -> int {
            for (int k = 0, t = 0;; ++k) {
                t += count[k];
                if (t >= i) {
                    return k;
                }
            }
        };
        int mi = 1 << 30, mx = -1;
        long long s = 0;
        int cnt = 0, mode = 0;
        for (int k = 0; k < count.size(); ++k) {
            if (count[k]) {
                mi = min(mi, k);
                mx = max(mx, k);
                s += 1LL * k * count[k];
                cnt += count[k];
                if (count[k] > count[mode]) {
                    mode = k;
                }
            }
        }
        double median = cnt % 2 == 1 ? find(cnt / 2 + 1) : (find(cnt / 2) + find(cnt / 2 + 1)) / 2.0;
        return vector<double>{(double) mi, (double) mx, s * 1.0 / cnt, median, (double) mode};
    }
};
```

### **Go**

```go
func sampleStats(count []int) []float64 {
	find := func(i int) int {
		for k, t := 0, 0; ; k++ {
			t += count[k]
			if t >= i {
				return k
			}
		}
	}
	mi, mx := 1<<30, -1
	s, cnt, mode := 0, 0, 0
	for k, x := range count {
		if x > 0 {
			mi = min(mi, k)
			mx = max(mx, k)
			s += k * x
			cnt += x
			if x > count[mode] {
				mode = k
			}
		}
	}
	var median float64
	if cnt&1 == 1 {
		median = float64(find(cnt/2 + 1))
	} else {
		median = float64(find(cnt/2)+find(cnt/2+1)) / 2
	}
	return []float64{float64(mi), float64(mx), float64(s) / float64(cnt), median, float64(mode)}
}
```

### **TypeScript**

```ts
function sampleStats(count: number[]): number[] {
    const find = (i: number): number => {
        for (let k = 0, t = 0; ; ++k) {
            t += count[k];
            if (t >= i) {
                return k;
            }
        }
    };
    let mi = 1 << 30;
    let mx = -1;
    let [s, cnt, mode] = [0, 0, 0];
    for (let k = 0; k < count.length; ++k) {
        if (count[k] > 0) {
            mi = Math.min(mi, k);
            mx = Math.max(mx, k);
            s += k * count[k];
            cnt += count[k];
            if (count[k] > count[mode]) {
                mode = k;
            }
        }
    }
    const median =
        cnt % 2 === 1 ? find((cnt >> 1) + 1) : (find(cnt >> 1) + find((cnt >> 1) + 1)) / 2;
    return [mi, mx, s / cnt, median, mode];
}
```

### **...**

```

```

<!-- tabs:end -->
