---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3890.Integers%20With%20Multiple%20Sum%20of%20Two%20Cubes/README.md
rating: 1534
source: 第 496 场周赛 Q2
tags:
    - 哈希表
    - 计数
    - 枚举
    - 排序
---

<!-- problem:start -->

# [3890. 可由多种立方和构造的整数](https://leetcode.cn/problems/integers-with-multiple-sum-of-two-cubes)

[English Version](/solution/3800-3899/3890.Integers%20With%20Multiple%20Sum%20of%20Two%20Cubes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>。</p>

<p>当存在<strong>&nbsp;至少&nbsp;</strong>两组不同的整数对 <code>(a, b)</code> 满足以下条件时，整数 <code>x</code> 被称为<strong>&nbsp;好整数</strong>：</p>

<ul>
	<li><code>a</code> 和 <code>b</code> 是正整数。</li>
	<li><code>a &lt;= b</code></li>
	<li><code>x = a<sup>3</sup> + b<sup>3</sup></code></li>
</ul>

<p>返回一个数组，其中包含所有小于等于 <code>n</code> 的好整数，并按升序排序。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4104</span></p>

<p><strong>输出：</strong> <span class="example-io">[1729,4104]</span></p>

<p><strong>解释：</strong></p>

<p>在小于等于 4104 的整数中，好整数包括：</p>

<ul>
	<li>1729：<code>1<sup>3</sup> + 12<sup>3</sup> = 1729</code>，以及 <code>9<sup>3</sup> + 10<sup>3</sup> = 1729</code>。</li>
	<li>4104：<code>2<sup>3</sup> + 16<sup>3</sup> = 4104</code>，以及 <code>9<sup>3</sup> + 15<sup>3</sup> = 4104</code>。</li>
</ul>

<p>因此，答案是 <code>[1729, 4104]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 578</span></p>

<p><strong>输出：</strong> <span class="example-io">[]</span></p>

<p><strong>解释：</strong></p>

<p>不存在小于等于 578 的好整数，因此答案是空数组。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 二分查找

我们注意到，当 $a$ 或 $b$ 大于 $1000$ 时，式子 $a^3 + b^3 > 10^9$。因此，我们只需要枚举 $1 \leq a \leq b \leq 1000$，统计每个整数 $x = a^3 + b^3$ 的出现次数。最后，筛选出出现次数大于 $1$ 的整数，并按升序排序，得到所有的好整数。

我们将所有好整数预处理出来，存储在数组 $\textit{GOOD}$ 中。对于每个查询，我们使用二分查找找到 $\textit{GOOD}$ 中第一个大于 $n$ 的整数的索引 $idx$，返回 $\textit{GOOD}$ 中前 $idx$ 个整数即可。

时间复杂度为 $O(m^2 + k \log k)$，其中 $m = 1000$ 是枚举的范围，而 $k$ 是好整数的数量。空间复杂度为 $O(k)$。

<!-- tabs:start -->

#### Python3

```python
LIMIT = 10**9

cnt = defaultdict(int)
cubes = [i**3 for i in range(1001)]

for a in range(1, 1001):
    for b in range(a, 1001):
        x = cubes[a] + cubes[b]
        if x > LIMIT:
            break
        cnt[x] += 1

GOOD = sorted(x for x, v in cnt.items() if v > 1)


class Solution:
    def findGoodIntegers(self, n: int) -> list[int]:
        idx = bisect_right(GOOD, n)
        return GOOD[:idx]
```

#### Java

```java
class Solution {
    private static final int LIMIT = (int) 1e9;
    private static final List<Integer> GOOD = new ArrayList<>();

    static {
        Map<Integer, Integer> cnt = new HashMap<>();
        int[] cubes = new int[1001];
        for (int i = 0; i <= 1000; i++) {
            cubes[i] = i * i * i;
        }
        for (int a = 1; a <= 1000; a++) {
            for (int b = a; b <= 1000; b++) {
                int x = cubes[a] + cubes[b];
                if (x > LIMIT) {
                    break;
                }
                cnt.merge(x, 1, Integer::sum);
            }
        }
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            if (e.getValue() > 1) {
                GOOD.add(e.getKey());
            }
        }

        Collections.sort(GOOD);
    }

    public List<Integer> findGoodIntegers(int n) {
        int idx = Collections.binarySearch(GOOD, n + 1);
        if (idx < 0) {
            idx = -idx - 1;
        }
        return GOOD.subList(0, idx);
    }
}
```

#### C++

```cpp
vector<int> GOOD;

auto init = [] {
    const int LIMIT = 1e9;

    unordered_map<int, int> cnt;
    vector<int> cubes(1001);

    for (int i = 0; i <= 1000; ++i) {
        cubes[i] = i * i * i;
    }

    for (int a = 1; a <= 1000; ++a) {
        for (int b = a; b <= 1000; ++b) {
            int x = cubes[a] + cubes[b];
            if (x > LIMIT) break;
            cnt[x]++;
        }
    }

    for (auto& [x, v] : cnt) {
        if (v > 1) {
            GOOD.push_back(x);
        }
    }

    sort(GOOD.begin(), GOOD.end());

    return 0;
}();

class Solution {
public:
    vector<int> findGoodIntegers(int n) {
        int idx = upper_bound(GOOD.begin(), GOOD.end(), n) - GOOD.begin();
        return vector<int>(GOOD.begin(), GOOD.begin() + idx);
    }
};
```

#### Go

```go
var GOOD []int

func init() {
	const LIMIT = 1e9

	cnt := make(map[int]int)
	cubes := make([]int, 1001)

	for i := 0; i <= 1000; i++ {
		cubes[i] = i * i * i
	}

	for a := 1; a <= 1000; a++ {
		for b := a; b <= 1000; b++ {
			x := cubes[a] + cubes[b]
			if x > LIMIT {
				break
			}
			cnt[x]++
		}
	}

	for x, v := range cnt {
		if v > 1 {
			GOOD = append(GOOD, x)
		}
	}

	sort.Ints(GOOD)
}

func findGoodIntegers(n int) []int {
	idx := sort.Search(len(GOOD), func(i int) bool {
		return GOOD[i] > n
	})
	return GOOD[:idx]
}
```

#### TypeScript

```ts
const LIMIT = 1e9;

const GOOD: number[] = (() => {
    const cnt = new Map<number, number>();
    const cubes: number[] = Array.from({ length: 1001 }, (_, i) => i * i * i);

    for (let a = 1; a <= 1000; a++) {
        for (let b = a; b <= 1000; b++) {
            const x = cubes[a] + cubes[b];
            if (x > LIMIT) break;
            cnt.set(x, (cnt.get(x) ?? 0) + 1);
        }
    }

    const res: number[] = [];
    for (const [x, v] of cnt.entries()) {
        if (v > 1) res.push(x);
    }

    res.sort((a, b) => a - b);
    return res;
})();

function findGoodIntegers(n: number): number[] {
    const idx = _.sortedLastIndex(GOOD, n);
    return GOOD.slice(0, idx);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
