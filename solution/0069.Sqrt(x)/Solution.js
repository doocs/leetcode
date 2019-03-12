/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function(x) {
    var left = 1
    var right = x    
    var middle = Math.floor((left + right) / 2)    
    while( middle !== left ) {
        if (middle * middle <= x) {
            left = middle
        } else {
            right = middle
        }
        middle = Math.floor((left + right) / 2)
    }    
    return middle
};
