## 最小栈
### 题目描述

设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

- push(x) -- 将元素 x 推入栈中。
- pop() -- 删除栈顶的元素。
- top() -- 获取栈顶元素。
- getMin() -- 检索栈中的最小元素。

**示例:**
```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```

### 解法
创建两个栈 `stack`, `help`，`help`作为辅助栈，每次压栈时，若 `help` 栈顶元素大于/等于要压入的元素 `x`，或者 `help` 栈为空，则压入 `x`，否则重复压入栈顶元素。取最小元素时，从 `help` 中获取栈顶元素即可。

```java
class MinStack {
    
    private Stack<Integer> stack;
    private Stack<Integer> help;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        help = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        help.push(help.isEmpty() || help.peek() >= x ? x : help.peek());
    }
    
    public void pop() {
        stack.pop();
        help.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return help.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```


然后还可以再优化一点点, 就是在辅助栈存最小值的索引, 这样可以避免辅助栈的大量的重复元素的情况. 

``` JavaScript
/**
 * initialize your data structure here.
 */
const MinStack = function() {
  this.arr = [];
  this.help = [];  
};

/** 
 * @param {number} x
 * @return {void}
 */
MinStack.prototype.push = function(x) {
    this.arr.push(x);
    if(this.help.length === 0){
      this.help.push(0);
    }else{
      let min = this.getMin();
      if(x < min){
        this.help.push(this.arr.length-1);
      }
    }
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function() {
    if(this.arr.length === 0){
      throw new Error('???');
    }
    if(this.arr.length - 1 === this.help[this.help.length - 1]){
      this.help.pop();
    }
    this.arr.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function() {
    return this.arr[this.arr.length-1];
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function() {
    if(this.arr.length === 0){
      throw new Error("???");
    }
    return this.arr[this.help[this.help.length-1]];
};

/** 
 * Your MinStack object will be instantiated and called as such:
 * var obj = Object.create(MinStack).createNew()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */

```

