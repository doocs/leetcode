const isValidSudoku = function(board){
  let existed = new Set();
  for(let i = 0; i < 9; i++){
    for(let j = 0; j < 9; j++){
      let number = board[i][j];
      if(board[i][j] !== '.'){
        if(existed.has(number + " in row " + i )||
          existed.has(number + ' in column ' + j) ||
          existed.has(number + 'in block ' + parseInt(i/3) + '-' + parseInt(j/3))){
            return false;
          }else{
            existed.add(number + " in row " + i );
            existed.add(number + ' in column ' + j);
            existed.add(number + 'in block ' + parseInt(i/3) + '-' + parseInt(j/3));
          }
      }
    }
  }
  return true;
};


/**
 * @param {character[][]} board
 * @return {boolean}
 */
const isValidSudoku2 = function(board){
  function lineTest(arr){
    for(let i = 0; i < 9; i++){
      for(let j = 0; j < 9; j++){
        for(let k = j+1; k < 9; k++){
          if(arr[i][j] === arr[i][k] && arr[i][j] !== '.'){
            return false;
          }
        }
      }
    }
    return true;
  }
  function columnTest(arr){
    for(let i = 0; i < 9; i++){
      for(let j = 0; j < 9;j++){
        for(let k = j+1; k < 9; k++){
          if(arr[j][i] === arr[k][i] && arr[j][i] !== '.'){

            return false;
          }
        }
      }
    }
    return true;
  }
  function squareTest(arr){
    let p = [[1,1],[1,4],[1,7],
            [4,1],[4,4],[4,7],
            [7,1],[7,4],[7,7]];
    
    for(let i = 0; i < p.length; i++){
      let a = [];
      let x = p[i][0];
      let y = p[i][1];
      a.push(arr[x-1][y-1]);
      a.push(arr[x-1][y]);
      a.push(arr[x-1][y+1]);
      a.push(arr[x][y-1]);
      a.push(arr[x][y]);
      a.push(arr[x][y+1]);
      a.push(arr[x+1][y-1]);
      a.push(arr[x+1][y]);
      a.push(arr[x+1][y+1]);
      for(let j = 0; j < a.length; j++){
        for(let k = j+1; k < a.length; k++){
          if(a[j] === a[k] && a[j] !== '.'){
            return false;
          }
        }
      }
    }
    return true;
  }
  return lineTest(board)&&columnTest(board)&&squareTest(board);
};
