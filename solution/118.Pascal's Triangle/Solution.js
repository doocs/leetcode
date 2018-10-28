const generate = function(numRows){
  let arr = [];
  for(let i = 0; i < numRows; i++){
    let row = [];
    row[0]=1;
    row[i] = 1;

    for(let j = 1; j < row.length - 1; j++){
      row[j] = arr[i-1][j-1] + arr[i-1][j];
    }
    arr.push(row);
  }
  return arr;
}
