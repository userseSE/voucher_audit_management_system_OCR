CREATE TRIGGER update_check_vou_info
BEFORE UPDATE ON vouchers
FOR EACH ROW
BEGIN
  UPDATE check_vou_info
  SET     vou_acc = NEW.vou_acc,
      con_acc = NEW.con_acc,
      amount = NEW.amount,
      need_vouch=NEW.need_vouch
  WHERE check_vou_info.comp = OLD.comp AND check_vou_info.vou_id = OLD.vou_id AND check_vou_info.vou_date=OLD.vou_date;
END;

CREATE TRIGGER insert_check_vou_info
AFTER INSERT ON vouchers
FOR EACH ROW
BEGIN
  INSERT INTO check_vou_info (comp,vou_id,vou_date,vou_acc,con_acc,amount,need_vouch)
  VALUES (NEW.comp, NEW.vou_id, NEW.vou_date,NEW.vou_acc,NEW.con_acc,NEW.amount,NEW.need_vouch);
END;

