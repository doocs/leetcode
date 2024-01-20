# [679. 24 Game](https://leetcode.com/problems/24-game)

[中文文档](/solution/0600-0699/0679.24%20Game/README.md)

## Description

<p>You are given an integer array <code>cards</code> of length <code>4</code>. You have four cards, each containing a number in the range <code>[1, 9]</code>. You should arrange the numbers on these cards in a mathematical expression using the operators <code>[&#39;+&#39;, &#39;-&#39;, &#39;*&#39;, &#39;/&#39;]</code> and the parentheses <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code> to get the value 24.</p>

<p>You are restricted with the following rules:</p>

<ul>
	<li>The division operator <code>&#39;/&#39;</code> represents real division, not integer division.

    <ul>
    	<li>For example, <code>4 / (1 - 2 / 3) = 4 / (1 / 3) = 12</code>.</li>
    </ul>
    </li>
    <li>Every operation done is between two numbers. In particular, we cannot use <code>&#39;-&#39;</code> as a unary operator.
    <ul>
    	<li>For example, if <code>cards = [1, 1, 1, 1]</code>, the expression <code>&quot;-1 - 1 - 1 - 1&quot;</code> is <strong>not allowed</strong>.</li>
    </ul>
    </li>
    <li>You cannot concatenate numbers together
    <ul>
    	<li>For example, if <code>cards = [1, 2, 1, 2]</code>, the expression <code>&quot;12 + 12&quot;</code> is not valid.</li>
    </ul>
    </li>

</ul>

<p>Return <code>true</code> if you can get such expression that evaluates to <code>24</code>, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cards = [4,1,8,7]
<strong>Output:</strong> true
<strong>Explanation:</strong> (8-4) * (7-1) = 24
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cards = [1,2,1,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>cards.length == 4</code></li>
	<li><code>1 &lt;= cards[i] &lt;= 9</code></li>
</ul>

## Solutions

### Solution 1: DFS

We design a function $dfs(nums)$, where $nums$ represents the current number sequence. The function returns a boolean value indicating whether there exists a permutation that makes this number sequence equal to $24$.

If the length of $nums$ is $1$, we return $true$ only when this number is $24$, otherwise we return $false$.

Otherwise, we can enumerate any two numbers $a$ and $b$ in $nums$ as the left and right operands, and enumerate the operator $op$ between $a$ and $b$. The result of $a\ op\ b$ can be used as an element of the new number sequence. We add it to the new number sequence and remove $a$ and $b$ from $nums$, then recursively call the $dfs$ function. If it returns $true$, it means we have found a permutation that makes this number sequence equal to $24$, and we return $true$.

If none of the enumerated cases return $true$, we return $false$.

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

<!-- end -->
