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