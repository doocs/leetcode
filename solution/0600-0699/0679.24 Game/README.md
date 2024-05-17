---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0679.24%20Game/README.md
tags:
    - 数组
    - 数学
    - 回溯
---

<!-- problem:start -->

# [679. 24 点游戏](https://leetcode.cn/problems/24-game)

[English Version](/solution/0600-0699/0679.24%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为4的整数数组&nbsp;<code>cards</code>&nbsp;。你有 <code>4</code> 张卡片，每张卡片上都包含一个范围在 <code>[1,9]</code> 的数字。您应该使用运算符&nbsp;<code>['+', '-', '*', '/']</code>&nbsp;和括号&nbsp;<code>'('</code>&nbsp;和&nbsp;<code>')'</code>&nbsp;将这些卡片上的数字排列成数学表达式，以获得值24。</p>

<p>你须遵守以下规则:</p>

<ul>
	<li>除法运算符 <code>'/'</code> 表示实数除法，而不是整数除法。

    <ul>
    	<li>例如，&nbsp;<code>4 /(1 - 2 / 3)= 4 /(1 / 3)= 12</code>&nbsp;。</li>
    </ul>
    </li>
    <li>每个运算都在两个数字之间。特别是，不能使用 <code>“-”</code> 作为一元运算符。
    <ul>
    	<li>例如，如果 <code>cards =[1,1,1,1]</code> ，则表达式 <code>“-1 -1 -1 -1”</code> 是 <strong>不允许</strong> 的。</li>
    </ul>
    </li>
    <li>你不能把数字串在一起
    <ul>
    	<li>例如，如果 <code>cards =[1,2,1,2]</code> ，则表达式 <code>“12 + 12”</code> 无效。</li>
    </ul>
    </li>

</ul>

<p>如果可以得到这样的表达式，其计算结果为 <code>24</code> ，则返回 <code>true </code>，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> cards = [4, 1, 8, 7]
<strong>输出:</strong> true
<strong>解释:</strong> (8-4) * (7-1) = 24
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> cards = [1, 2, 1, 2]
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>cards.length == 4</code></li>
	<li><code>1 &lt;= cards[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们设计一个函数 $dfs(nums)$，其中 $nums$ 表示当前的数字序列，函数返回一个布尔值，表示是否存在一种排列方式，使得这个数字序列可以得到 $24$。

如果 $nums$ 的长度为 $1$，那么只有当这个数字等于 $24$ 时，我们才返回 $true$，否则返回 $false$。

否则，我们可以枚举 $nums$ 中的任意两个数 $a$ 和 $b$ 作为左右两个操作数，枚举 $a$ 和 $b$ 之间的运算符 $op$，那么 $a\ op\ b$ 的结果就可以作为新的数字序列的一个元素，我们将其加入到新的数字序列中，并从 $nums$ 中移除 $a$ 和 $b$，然后递归地调用 $dfs$ 函数，如果返回 $true$，那么我们就找到了一种排列方式，使得这个数字序列可以得到 $24$，我们就返回 $true$。

如果枚举完所有的情况都没有返回 $true$，那么我们就返回 $false$。

<!-- tabs:start -->

```python
class Solution:
    def judgePoint24(self, cards: List[int]) -> bool:
        def dfs(nums: List[float]):
            n = len(nums)
            if n == 1:
                if abs(nums[0] - 24) < 1e-6:
                    return True
                return False
            ok = False
            for i in range(n):
                for j in range(n):
                    if i != j:
                        nxt = [nums[k] for k in range(n) if k != i and k != j]
                        for op in ops:
                            match op:
                                case "/":
                                    if nums[j] == 0:
                                        continue
                                    ok |= dfs(nxt + [nums[i] / nums[j]])
                                case "*":
                                    ok |= dfs(nxt + [nums[i] * nums[j]])
                                case "+":
                                    ok |= dfs(nxt + [nums[i] + nums[j]])
                                case "-":
                                    ok |= dfs(nxt + [nums[i] - nums[j]])
                            if ok:
                                return True
            return ok

        ops = ("+", "-", "*", "/")
        nums = [float(x) for x in cards]
        return dfs(nums)
```

```java
class Solution {
    private final char[] ops = {'+', '-', '*', '/'};

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int num : cards) {
            nums.add((double) num);
        }
        return dfs(nums);
    }

    private boolean dfs(List<Double> nums) {
        int n = nums.size();
        if (n == 1) {
            return Math.abs(nums.get(0) - 24) < 1e-6;
        }
        boolean ok = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    List<Double> nxt = new ArrayList<>();
                    for (int k = 0; k < n; ++k) {
                        if (k != i && k != j) {
                            nxt.add(nums.get(k));
                        }
                    }
                    for (char op : ops) {
                        switch (op) {
                            case '/' -> {
                                if (nums.get(j) == 0) {
                                    continue;
                                }
                                nxt.add(nums.get(i) / nums.get(j));
                            }
                            case '*' -> {
                                nxt.add(nums.get(i) * nums.get(j));
                            }
                            case '+' -> {
                                nxt.add(nums.get(i) + nums.get(j));
                            }
                            case '-' -> {
                                nxt.add(nums.get(i) - nums.get(j));
                            }
                        }
                        ok |= dfs(nxt);
                        if (ok) {
                            return true;
                        }
                        nxt.remove(nxt.size() - 1);
                    }
                }
            }
        }
        return ok;
    }
}
```

```cpp
class Solution {
public:
    bool judgePoint24(vector<int>& cards) {
        vector<double> nums;
        for (int num : cards) {
            nums.push_back(static_cast<double>(num));
        }
        return dfs(nums);
    }

private:
    const char ops[4] = {'+', '-', '*', '/'};

    bool dfs(vector<double>& nums) {
        int n = nums.size();
        if (n == 1) {
            return abs(nums[0] - 24) < 1e-6;
        }
        bool ok = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    vector<double> nxt;
                    for (int k = 0; k < n; ++k) {
                        if (k != i && k != j) {
                            nxt.push_back(nums[k]);
                        }
                    }
                    for (char op : ops) {
                        switch (op) {
                        case '/':
                            if (nums[j] == 0) {
                                continue;
                            }
                            nxt.push_back(nums[i] / nums[j]);
                            break;
                        case '*':
                            nxt.push_back(nums[i] * nums[j]);
                            break;
                        case '+':
                            nxt.push_back(nums[i] + nums[j]);
                            break;
                        case '-':
                            nxt.push_back(nums[i] - nums[j]);
                            break;
                        }
                        ok |= dfs(nxt);
                        if (ok) {
                            return true;
                        }
                        nxt.pop_back();
                    }
                }
            }
        }
        return ok;
    }
};
```

```go
func judgePoint24(cards []int) bool {
	ops := [4]rune{'+', '-', '*', '/'}
	nums := make([]float64, len(cards))
	for i, num := range cards {
		nums[i] = float64(num)
	}
	var dfs func([]float64) bool
	dfs = func(nums []float64) bool {
		n := len(nums)
		if n == 1 {
			return math.Abs(nums[0]-24) < 1e-6
		}
		ok := false
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				if i != j {
					var nxt []float64
					for k := 0; k < n; k++ {
						if k != i && k != j {
							nxt = append(nxt, nums[k])
						}
					}
					for _, op := range ops {
						switch op {
						case '/':
							if nums[j] == 0 {
								continue
							}
							nxt = append(nxt, nums[i]/nums[j])
						case '*':
							nxt = append(nxt, nums[i]*nums[j])
						case '+':
							nxt = append(nxt, nums[i]+nums[j])
						case '-':
							nxt = append(nxt, nums[i]-nums[j])
						}
						ok = ok || dfs(nxt)
						if ok {
							return true
						}
						nxt = nxt[:len(nxt)-1]
					}
				}
			}
		}
		return ok
	}

	return dfs(nums)
}
```

```ts
function judgePoint24(cards: number[]): boolean {
    const ops: string[] = ['+', '-', '*', '/'];
    const dfs = (nums: number[]): boolean => {
        const n: number = nums.length;
        if (n === 1) {
            return Math.abs(nums[0] - 24) < 1e-6;
        }
        let ok: boolean = false;
        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                if (i !== j) {
                    const nxt: number[] = [];
                    for (let k = 0; k < n; k++) {
                        if (k !== i && k !== j) {
                            nxt.push(nums[k]);
                        }
                    }
                    for (const op of ops) {
                        switch (op) {
                            case '/':
                                if (nums[j] === 0) {
                                    continue;
                                }
                                nxt.push(nums[i] / nums[j]);
                                break;
                            case '*':
                                nxt.push(nums[i] * nums[j]);
                                break;
                            case '+':
                                nxt.push(nums[i] + nums[j]);
                                break;
                            case '-':
                                nxt.push(nums[i] - nums[j]);
                                break;
                        }
                        ok = ok || dfs(nxt);
                        if (ok) {
                            return true;
                        }
                        nxt.pop();
                    }
                }
            }
        }
        return ok;
    };

    return dfs(cards);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
