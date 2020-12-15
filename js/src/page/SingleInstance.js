
console.log('load SingleInstance.js');
export default class SingleInstance {
  constructor(name) {
    this.name = name;
    this.instance = null;
  }

  static getInstance(){
    if(!this.instance){
      this.instance = new SingleInstance("lvjie");
    }
    return this.instance;
  }

  setName(name){
    this.name = name;
  }

  getName(){
    return this.name;
  }

}
