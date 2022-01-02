'use strict';

const pipe = (...fns) => {
    fns.forEach(fn => {
        if (typeof fn !== 'function') throw new TypeError('function expected');
    });
    return x => {
        fns.forEach(fn => x = fn(x));
        return x;
    }
}

module.exports = { pipe };
