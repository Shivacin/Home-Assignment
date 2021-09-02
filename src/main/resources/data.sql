insert into condition (id, cond, description) values 
	(1, 'New',
	'A brand-new, unused, unopened, undamaged item in its original packaging (where packaging is applicable). Packaging should be the same as what is found in a retail store, unless the item is handmade or was packaged by the manufacturer in non-retail packaging, such as an unprinted box or plastic bag. See the seller''s listing for full details.'),

	(2, '''New other (see details)''',
	'A new, unused item with absolutely no signs of wear. The item may be missing the original packaging, or in the original packaging but not sealed. The item may be a factory second or a new, unused item with defects. See the seller''s listing for full details and description of any imperfections.'),

	(3, 'Open box',
	'An item in excellent, new condition with no wear. The item may be missing the original packaging or protective wrapping, or may be in the original packaging but not sealed. The item includes original accessories. The item may be a factory second. See the seller''s listing for full details and description.'),

	(4, 'Certified refurbished',
	'The item is in a pristine, like-new condition. It has been professionally inspected, cleaned, and refurbished by the manufacturer or a manufacturer-approved vendor to meet manufacturer specifications. The item will be in new packaging with original or new accessories. See the seller''s listing for full details.'),

	(5, 'Seller refurbished',
	'The item has been restored to working order by the eBay seller or a third party. This means the item was inspected, cleaned, and repaired to full working order and is in excellent condition. This item may or may not be in original packaging. See seller''s listing for full details.'),

	(6, 'Used',
	'An item that has been used previously. The item may have some signs of cosmetic wear, but is fully operational and functions as intended. This item may be a floor model or store return that has been used. See the seller''s listing for full details and description of any imperfections.'),

	(7, 'For parts or not working',
	'An item that does not function as intended and is not fully operational. This includes items that are defective in ways that render them difficult to use, items that require service or repair, items that are locked or can''t be activated, or items missing essential components. See the seller''s listing for full details.');


insert into item (id, title, condition, price, quantity, description) values
	(1, 
	'NEW Black & Decker 75-530 Jig Saw Blade Set, 5 Pieces, Universal Shank 6716419', 
	1, 
	59.99, 
	1,
	'Description: NEW Black & Decker 75-530 Jig Saw Blade Set, 5 Pieces, Universal Shank 6716419'),
	(2, 
	'Black & Decker OEM N389203 replacement circular saw blade PCC661', 
	1, 
	19.53, 
	5,
	'Black & Decker OEM N389203 replacement circular saw blade PCC661');

insert into item_specs (spec, spec_val, item_id) values
	('Brand', 'BLACK+DECKER', 1),
	('UPC', '028874755307', 1),
	('MPN', '75-530', 1),
	('Battery Included', 'No (Body Only)', 2),
	('Battery Type', 'Rechargeable Alkaline', 2),
	('UPC', '694026426076', 2),
	('Brand', 'Black & Decker', 2),
	('Type', 'Home & Garden', 2),
	('MPN', 'N389203', 2),
	('Power Source', 'Does Not Apply', 2);

insert into item_image_url (url, item_id) values
	('https://cdn.shopify.com/s/files/1/2196/8707/products/17d82d193913dd503ea29f54348b4e69_40be2e28-5aa6-4c75-a01c-5ff6f6c10431_1200x.jpg?v=1571723498', 1),
	('https://upcitemdb.s3.amazonaws.com/1862-570397655.webp', 1),
	('https://i.ebayimg.com/images/g/khgAAOSwq1ld-70Y/s-l1600.jpg', 2);
