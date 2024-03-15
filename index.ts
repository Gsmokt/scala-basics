// ok some shit with no sense
// but would be funny with recursive checking
// and recrusive receving calls (some modules class) from each other
// at the same time

interface Obj {
  field1: number;
  field2: string;
  field3: boolean;
}

function checkType(tp: any): tp is Obj {
  return "field1" in tp && "field2" in tp && "field3" in tp;
}

function returnPartial(obj: Partial<Obj>): Partial<Obj> {
  return obj;
}

function funcOne() {
  const obj: any = {};
  return (type: unknown) => {
    if (checkType(obj)) {
      console.log("OK");
      return ReturnClass(obj);
    }
    if (checkType(obj)) {
      return ReturnClass(type as Obj);
    }
    if (typeof type == "object") {
      console.log(obj);
      obj.field1 = type;
      console.log(returnPartial(obj));
    }
    if (typeof type == "string") {
      console.log(obj);
      obj.field2 = type;
      returnPartial(obj);
    }
    if (typeof type == "boolean") {
      console.log(obj);
      obj.field3 = type;
      returnPartial(obj);
    }
  };
}

function ReturnClass(obj: Obj) {
  return class {
    field1: number;
    field2: string;
    field3: boolean;
    constructor() {
      this.field1 = obj.field1;
      this.field2 = obj.field2;
      this.field3 = obj.field3;
    }
  };
}

const check = funcOne();
// const part = check(2);
const part = check(Promise.resolve<number>(2));
const part2 = check(Promise.resolve<Partial<Obj>>({ field2: "2" }));
const part3 = check(Promise.resolve<Partial<Obj>>({ field3: true }));
console.log(part3);
