'use strict';

const pipe = (...fns) =>{
   fns.map(fn => {
       if (typeof fn !== 'function') throw new TypeError('function expected');
   })
    return x => {
        fns.map(fn => x = fn(x));
        return x;
    }
} 
module.exports = { pipe };
