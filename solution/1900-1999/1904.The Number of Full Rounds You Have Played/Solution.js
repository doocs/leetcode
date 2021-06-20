/**
 * @param {string} startTime
 * @param {string} finishTime
 * @return {number}
 */
 var numberOfRounds = function(startTime, finishTime) {
    let p1 = Math.ceil(toMinutes(startTime) / 15);
    let p2 = Math.floor(toMinutes(finishTime) / 15);

    if (p1 > p2) {
        p2 += 24 * 60 / 15;
    }

    let ans = p2 - p1;
    return ans;
};

function toMinutes(time) {
    let [h, m] = time.split(':');
    return Number(h) * 60 + Number(m);
}