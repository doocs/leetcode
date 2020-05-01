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