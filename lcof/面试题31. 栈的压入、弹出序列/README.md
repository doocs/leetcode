# [面试题31. 栈的压入、弹出序列](https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

**示例 1：**

```
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
```

**示例 2：**

```
输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```

**提示：**

1. `0 <= pushed.length == popped.length <= 1000`
2. `0 <= pushed[i], popped[i] < 1000`
3. `pushed` 是 `popped` 的排列。

## 解法
<!-- 这里可写通用的实现逻辑 -->
借助一个辅助栈实现。

<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        t = []
        for num in popped:
            while len(t) == 0 or t[-1] != num:
                if len(pushed) == 0:
                    return False
                t.append(pushed[0])
                pushed = pushed[1:]
            t.pop()
        return True
            
```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> t = new Stack<>();
        int i = 0, n = pushed.length;
        for (int num : popped) {
            while (t.empty() || t.peek() != num) {
                if (i == n) {
                    return false;
                }
                t.push(pushed[i++]);
            }
            t.pop();
        }
        return true;
    }
}
```

### **JavaScript**
```js
/**
 * @param {number[]} pushed
 * @param {number[]} popped
 * @return {boolean}
 */
var validateStackSequences = function(pushed, popped) {
    let stack = []
    while(pushed.length && popped.length) {
        if(pushed[0] === popped[0]) {
            pushed.shift()
            popped.shift()
        } else if(popped[0] === stack[0]) {
            stack.shift()
            popped.shift()
        } else {
            stack.unshift(pushed.shift())
        }
    }
    while(stack.length) {
        if(stack[0] !== popped[0]) return false
        stack.shift()
        popped.shift()
    }
    return true
};
```

### **...**
```

```

<!-- tabs:end -->