---
comments: true
difficulty: 中等
tags:
    - 栈
    - 深度优先搜索
    - 字符串
---

<!-- problem:start -->

# [385. 迷你语法分析器](https://leetcode.cn/problems/mini-parser)

[English Version](/solution/0300-0399/0385.Mini%20Parser/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果&nbsp;<code>NestedInteger</code> 。</p>

<p>列表中的每个元素只可能是整数或整数嵌套列表</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "324",
<strong>输出：</strong>324
<strong>解释：</strong>你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "[123,[456,[789]]]",
<strong>输出：</strong>[123,[456,[789]]]
<strong>解释：</strong>返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
1. 一个 integer 包含值 123
2. 一个包含两个元素的嵌套列表：
    i.  一个 integer 包含值 456
    ii. 一个包含一个元素的嵌套列表
         a. 一个 integer 包含值 789
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> 由数字、方括号&nbsp;<code>"[]"</code>、负号&nbsp;<code>'-'</code>&nbsp;、逗号&nbsp;<code>','</code>组成</li>
	<li>用例保证&nbsp;<code>s</code> 是可解析的&nbsp;<code>NestedInteger</code></li>
	<li>输入中的所有值的范围是&nbsp;<code>[-10<sup>6</sup>, 10<sup>6</sup>]</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：显式栈

<!-- thinking:start -->

> **思考**
>
> 把嵌套列表字符串解析成 `NestedInteger`。文法是整数或 `[元素,…,元素]`，递归下降直观，但嵌套层数可达数千。
>
> 用显式栈保存尚未闭合的列表。扫描字符时累积整数，遇到逗号或右括号就加入当前列表；右括号再将子列表挂到父列表。栈随输入深度增长，不占用调用栈。

<!-- thinking:end -->

如果 $s$ 表示整数，我们直接返回包含该值的 `NestedInteger`。否则从左向右扫描：每遇到 `[` 就创建列表并压入栈，数字和符号组成当前整数；遇到逗号或 `]` 时，将已完成的整数加入栈顶列表，遇到 `]` 再弹出子列表并加入父列表。显式栈避免调用深度随输入嵌套增长。

遍历结束后，返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#    def __init__(self, value=None):
#        """
#        If value is not specified, initializes an empty list.
#        Otherwise initializes a single integer equal to value.
#        """
#
#    def isInteger(self):
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        :rtype bool
#        """
#
#    def add(self, elem):
#        """
#        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
#        :rtype void
#        """
#
#    def setInteger(self, value):
#        """
#        Set this NestedInteger to hold a single integer equal to value.
#        :rtype void
#        """
#
#    def getInteger(self):
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        :rtype int
#        """
#
#    def getList(self):
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        :rtype List[NestedInteger]
#        """
class Solution:
    def deserialize(self, s: str) -> NestedInteger:
        if s[0] != '[':
            return NestedInteger(int(s))
        stack = []
        root = None
        num = 0
        negative = False
        for i, c in enumerate(s):
            if c == '[':
                node = NestedInteger()
                if stack:
                    stack[-1].add(node)
                else:
                    root = node
                stack.append(node)
            elif c == '-':
                negative = True
            elif c.isdigit():
                num = num * 10 + int(c)
            elif c in ',]':
                if s[i - 1].isdigit():
                    stack[-1].add(NestedInteger(-num if negative else num))
                num = 0
                negative = False
                if c == ']':
                    stack.pop()
        return root
```

#### Java

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        Deque<NestedInteger> stack = new ArrayDeque<>();
        int num = 0;
        boolean negative = false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '-') {
                negative = true;
            } else if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                stack.push(new NestedInteger());
            } else if (c == ',' || c == ']') {
                if (Character.isDigit(s.charAt(i - 1))) {
                    stack.peek().add(new NestedInteger(negative ? -num : num));
                }
                num = 0;
                negative = false;
                if (c == ']' && stack.size() > 1) {
                    NestedInteger child = stack.pop();
                    stack.peek().add(child);
                }
            }
        }
        return stack.peek();
    }
}
```

#### C++

```cpp
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Constructor initializes an empty nested list.
 *     NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     NestedInteger(int value);
 *
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Set this NestedInteger to hold a single integer.
 *     void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     void add(const NestedInteger &ni);
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */
class Solution {
public:
    NestedInteger deserialize(string s) {
        if (s[0] != '[') {
            return NestedInteger(stoi(s));
        }
        stack<NestedInteger> stk;
        int num = 0;
        bool negative = false;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '-') {
                negative = true;
            } else if (isdigit(s[i])) {
                num = num * 10 + s[i] - '0';
            } else if (s[i] == '[') {
                stk.push(NestedInteger());
            } else if (s[i] == ',' || s[i] == ']') {
                if (isdigit(s[i - 1])) {
                    stk.top().add(NestedInteger(negative ? -num : num));
                }
                num = 0;
                negative = false;
                if (s[i] == ']' && stk.size() > 1) {
                    auto child = stk.top();
                    stk.pop();
                    stk.top().add(child);
                }
            }
        }
        return stk.top();
    }
};
```

#### Go

```go
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * type NestedInteger struct {
 * }
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * func (n NestedInteger) IsInteger() bool {}
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * // So before calling this method, you should have a check
 * func (n NestedInteger) GetInteger() int {}
 *
 * // Set this NestedInteger to hold a single integer.
 * func (n *NestedInteger) SetInteger(value int) {}
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * func (n *NestedInteger) Add(elem NestedInteger) {}
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The list length is zero if this NestedInteger holds a single integer
 * // You can access NestedInteger's List element directly if you want to modify it
 * func (n NestedInteger) GetList() []*NestedInteger {}
 */
func deserialize(s string) *NestedInteger {
	if s[0] != '[' {
		ans := &NestedInteger{}
		v, _ := strconv.Atoi(s)
		ans.SetInteger(v)
		return ans
	}
	stack := []*NestedInteger{}
	num := 0
	negative := false
	for i := 0; i < len(s); i++ {
		c := s[i]
		if c == '-' {
			negative = true
		} else if c >= '0' && c <= '9' {
			num = num*10 + int(c-'0')
		} else if c == '[' {
			stack = append(stack, &NestedInteger{})
		} else if c == ',' || c == ']' {
			if s[i-1] >= '0' && s[i-1] <= '9' {
				value := num
				if negative {
					value = -value
				}
				child := &NestedInteger{}
				child.SetInteger(value)
				stack[len(stack)-1].Add(*child)
			}
			num = 0
			negative = false
			if c == ']' && len(stack) > 1 {
				child := stack[len(stack)-1]
				stack = stack[:len(stack)-1]
				stack[len(stack)-1].Add(*child)
			}
		}
	}
	return stack[0]
}
```

#### TypeScript

```ts
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     If value is provided, then it holds a single integer
 *     Otherwise it holds an empty nested list
 *     constructor(value?: number) {
 *         ...
 *     };
 *
 *     Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     isInteger(): boolean {
 *         ...
 *     };
 *
 *     Return the single integer that this NestedInteger holds, if it holds a single integer
 *     Return null if this NestedInteger holds a nested list
 *     getInteger(): number | null {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a single integer equal to value.
 *     setInteger(value: number) {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
 *     add(elem: NestedInteger) {
 *         ...
 *     };
 *
 *     Return the nested list that this NestedInteger holds,
 *     or an empty list if this NestedInteger holds a single integer
 *     getList(): NestedInteger[] {
 *         ...
 *     };
 * };
 */

function deserialize(s: string): NestedInteger {
    if (s[0] !== '[') {
        return new NestedInteger(+s);
    }
    const stack: NestedInteger[] = [];
    let num = 0;
    let negative = false;
    for (let i = 0; i < s.length; ++i) {
        const c = s[i];
        if (c === '-') {
            negative = true;
        } else if (c >= '0' && c <= '9') {
            num = num * 10 + c.charCodeAt(0) - '0'.charCodeAt(0);
        } else if (c === '[') {
            stack.push(new NestedInteger());
        } else if (c === ',' || c === ']') {
            if (s[i - 1] >= '0' && s[i - 1] <= '9') {
                stack[stack.length - 1].add(new NestedInteger(negative ? -num : num));
            }
            num = 0;
            negative = false;
            if (c === ']' && stack.length > 1) {
                const child = stack.pop()!;
                stack[stack.length - 1].add(child);
            }
        }
    }
    return stack[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：栈

<!-- thinking:start -->

> **思考**
>
> 递归依赖调用栈。改为显式栈：遇 `[` 压入新列表，遇数字累积，遇 `,`/`]` 把整数或子列表挂到栈顶。右括号且栈深于 $1$ 时弹出并加入外层。避免递归深度。

<!-- thinking:end -->

我们可以使用栈来模拟递归的过程。

我们首先判断字符串 $s$ 是否是一个整数，如果是，直接返回一个包含这个整数的 `NestedInteger`。否则，我们从左到右遍历字符串 $s$，对于当前遍历到的字符 $c$：

- 如果 $c$ 是负号，我们将负号标识置为 `true`；
- 如果 $c$ 是数字，我们将数字加入到当前数字 $x$ 中，其中 $x$ 的初始值为 $0$；
- 如果 $c$ 是左括号，我们将一个新的 `NestedInteger` 压入栈中；
- 如果 $c$ 是右括号或者逗号，我们判断当前字符的前一个字符是否是数字，如果是，我们根据负号标识将当前数字 $x$ 加入到栈顶的 `NestedInteger` 中，然后将负号标识置为 `false`，当前数字 $x$ 重置为 $0$。如果 $c$ 是右括号，并且当前栈的大小大于 $1$，我们将栈顶的 `NestedInteger` 出栈，将其加入到栈顶的 `NestedInteger` 中。

遍历结束后，返回栈顶的 `NestedInteger` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#    def __init__(self, value=None):
#        """
#        If value is not specified, initializes an empty list.
#        Otherwise initializes a single integer equal to value.
#        """
#
#    def isInteger(self):
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        :rtype bool
#        """
#
#    def add(self, elem):
#        """
#        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
#        :rtype void
#        """
#
#    def setInteger(self, value):
#        """
#        Set this NestedInteger to hold a single integer equal to value.
#        :rtype void
#        """
#
#    def getInteger(self):
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        :rtype int
#        """
#
#    def getList(self):
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        :rtype List[NestedInteger]
#        """
class Solution:
    def deserialize(self, s: str) -> NestedInteger:
        if s[0] != '[':
            return NestedInteger(int(s))
        stk, x, neg = [], 0, False
        for i, c in enumerate(s):
            if c == '-':
                neg = True
            elif c.isdigit():
                x = x * 10 + int(c)
            elif c == '[':
                stk.append(NestedInteger())
            elif c in ',]':
                if s[i - 1].isdigit():
                    if neg:
                        x = -x
                    stk[-1].add(NestedInteger(x))
                x, neg = 0, False
                if c == ']' and len(stk) > 1:
                    t = stk.pop()
                    stk[-1].add(t)
        return stk.pop()
```

#### Java

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        Deque<NestedInteger> stk = new ArrayDeque<>();
        int x = 0;
        boolean neg = false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '-') {
                neg = true;
            } else if (Character.isDigit(c)) {
                x = x * 10 + c - '0';
            } else if (c == '[') {
                stk.push(new NestedInteger());
            } else if (c == ',' || c == ']') {
                if (Character.isDigit(s.charAt(i - 1))) {
                    if (neg) {
                        x = -x;
                    }
                    stk.peek().add(new NestedInteger(x));
                }
                x = 0;
                neg = false;
                if (c == ']' && stk.size() > 1) {
                    NestedInteger t = stk.pop();
                    stk.peek().add(t);
                }
            }
        }
        return stk.peek();
    }
}
```

#### C++

```cpp
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Constructor initializes an empty nested list.
 *     NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     NestedInteger(int value);
 *
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Set this NestedInteger to hold a single integer.
 *     void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     void add(const NestedInteger &ni);
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */
class Solution {
public:
    NestedInteger deserialize(string s) {
        if (s[0] != '[') {
            return NestedInteger(stoi(s));
        }
        stack<NestedInteger> stk;
        int x = 0;
        bool neg = false;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '-') {
                neg = true;
            } else if (isdigit(s[i])) {
                x = x * 10 + s[i] - '0';
            } else if (s[i] == '[') {
                stk.push(NestedInteger());
            } else if (s[i] == ',' || s[i] == ']') {
                if (isdigit(s[i - 1])) {
                    if (neg) {
                        x = -x;
                    }
                    stk.top().add(NestedInteger(x));
                }
                x = 0;
                neg = false;
                if (s[i] == ']' && stk.size() > 1) {
                    auto t = stk.top();
                    stk.pop();
                    stk.top().add(t);
                }
            }
        }
        return stk.top();
    }
};
```

#### Go

```go
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * type NestedInteger struct {
 * }
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * func (n NestedInteger) IsInteger() bool {}
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * // So before calling this method, you should have a check
 * func (n NestedInteger) GetInteger() int {}
 *
 * // Set this NestedInteger to hold a single integer.
 * func (n *NestedInteger) SetInteger(value int) {}
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * func (n *NestedInteger) Add(elem NestedInteger) {}
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The list length is zero if this NestedInteger holds a single integer
 * // You can access NestedInteger's List element directly if you want to modify it
 * func (n NestedInteger) GetList() []*NestedInteger {}
 */
func deserialize(s string) *NestedInteger {
	if s[0] != '[' {
		v, _ := strconv.Atoi(s)
		ans := NestedInteger{}
		ans.SetInteger(v)
		return &ans
	}
	stk := []*NestedInteger{}
	x := 0
	neg := false
	for i, c := range s {
		if c == '-' {
			neg = true
		} else if c >= '0' && c <= '9' {
			x = x*10 + int(c-'0')
		} else if c == '[' {
			stk = append(stk, &NestedInteger{})
		} else if c == ',' || c == ']' {
			if s[i-1] >= '0' && s[i-1] <= '9' {
				if neg {
					x = -x
				}
				t := NestedInteger{}
				t.SetInteger(x)
				stk[len(stk)-1].Add(t)
			}
			x = 0
			neg = false
			if c == ']' && len(stk) > 1 {
				t := stk[len(stk)-1]
				stk = stk[:len(stk)-1]
				stk[len(stk)-1].Add(*t)
			}
		}
	}
	return stk[0]
}
```

#### TypeScript

```ts
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     If value is provided, then it holds a single integer
 *     Otherwise it holds an empty nested list
 *     constructor(value?: number) {
 *         ...
 *     };
 *
 *     Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     isInteger(): boolean {
 *         ...
 *     };
 *
 *     Return the single integer that this NestedInteger holds, if it holds a single integer
 *     Return null if this NestedInteger holds a nested list
 *     getInteger(): number | null {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a single integer equal to value.
 *     setInteger(value: number) {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
 *     add(elem: NestedInteger) {
 *         ...
 *     };
 *
 *     Return the nested list that this NestedInteger holds,
 *     or an empty list if this NestedInteger holds a single integer
 *     getList(): NestedInteger[] {
 *         ...
 *     };
 * };
 */

function deserialize(s: string): NestedInteger {
    if (s[0] !== '[') {
        return new NestedInteger(+s);
    }
    const stk: NestedInteger[] = [];
    let x = 0;
    let neg = false;
    for (let i = 0; i < s.length; ++i) {
        if (s[i] === '-') {
            neg = true;
        } else if (s[i] === '[') {
            stk.push(new NestedInteger());
        } else if (s[i] >= '0' && s[i] <= '9') {
            x = x * 10 + s[i].charCodeAt(0) - '0'.charCodeAt(0);
        } else if (s[i] === ',' || s[i] === ']') {
            if (s[i - 1] >= '0' && s[i - 1] <= '9') {
                stk[stk.length - 1].add(new NestedInteger(neg ? -x : x));
            }
            x = 0;
            neg = false;
            if (s[i] === ']' && stk.length > 1) {
                const t = stk.pop()!;
                stk[stk.length - 1].add(t);
            }
        }
    }
    return stk[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
