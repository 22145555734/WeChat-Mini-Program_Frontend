// miniprogram/utils/storage.js

const KEYS = {
  CART: "BOOK_CART",
  ORDERS: "BOOK_ORDERS",
  CHECKOUT_ITEMS: "CHECKOUT_ITEMS",
};

function getCart() {
  return wx.getStorageSync(KEYS.CART) || [];
}

function setCart(cart) {
  wx.setStorageSync(KEYS.CART, cart || []);
}

function getOrders() {
  return wx.getStorageSync(KEYS.ORDERS) || [];
}

function setOrders(orders) {
  wx.setStorageSync(KEYS.ORDERS, orders || []);
}

function createOrder({ items = [], address = null, remark = "" }) {
  const totalCount = items.reduce((sum, i) => sum + Number(i.count || 0), 0);
  const totalPrice = Number(
    items
      .reduce((sum, i) => sum + Number(i.price || 0) * Number(i.count || 0), 0)
      .toFixed(2),
  );

  const order = {
    id: "OD" + Date.now(), // 简单订单号
    createTime: Date.now(),
    status: "PENDING", // PENDING / PAID / CANCELED
    items: items.map((i) => ({
      id: i.id,
      name: i.name || i.title || "",
      cover: i.cover,
      price: Number(i.price || 0),
      count: Number(i.count || 0),
    })),
    totalCount,
    totalPrice,
    address,
    remark,
  };

  const orders = getOrders();
  orders.unshift(order); // 最新在前
  setOrders(orders);

  return order;
}

module.exports = {
  KEYS,
  getCart,
  setCart,
  getOrders,
  setOrders,
  createOrder,
};
