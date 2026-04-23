const ORDER_KEY = "BOOK_ORDERS";

function getOrders() {
  return wx.getStorageSync(ORDER_KEY) || [];
}

function setOrders(orders) {
  wx.setStorageSync(ORDER_KEY, orders);
}

function createOrder(payload) {
  const orders = getOrders();

  const order = {
    id: "OD" + Date.now(), // 简单订单号
    createTime: new Date().toLocaleString(),
    status: "待支付",
    ...payload, // items, totalPrice, totalCount, address
  };

  orders.unshift(order); // 新订单放最前
  setOrders(orders);
  return order;
}

module.exports = {
  getOrders,
  setOrders,
  createOrder,
};
