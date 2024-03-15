// this shit even works, but uesless

function Construct() {
  const promise = new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve("Hello");
    }, 2000);
  });

  async function getPromise() {
    this.result = await promise;
    console.log(this);
  }

  return getPromise;
}

function Receiver() {
  this.result = null;
}

// Receiver.prototype.getResult = function () {
//   return this.result;
// };

const receive = new Receiver();

const con = Construct().bind(receive);

await con();
// setTimeout(() => {
console.log(receive);
// }, 3000);
