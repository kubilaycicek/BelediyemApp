export class MenuConfig {
	public defaults: any = {
		header: {
			self: {},
			items: [
				{
					section: 'Yeni Kayıtlar',
					state: 'show'
				},
				{
					title: 'My-Page',
					root: true,
					icon: 'flaticon2-add-1',
					page: '/my-page',
					translate: 'My-Page',
					bullet: 'dot',
					state: 'hide'
				},
				{
					title: 'User-Add',
					root: true,
					icon: 'flaticon2-add-1',
					page: '/user-add',
					translate: 'Kullanıcı Ekle',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Category-Add',
					root: true,
					icon: 'flaticon2-writing',
					page: '/category-add',
					translate: 'Kategori Ekle',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Department-Add',
					root: true,
					icon: 'flaticon2-layers-2',
					page: '/department-add',
					translate: 'Departman Ekle',
					bullet: 'dot',
					state: 'show'
				},
				{
					section: 'Kayıtları Listele',
					state: 'show'
				},
				{
					title: 'User-List',
					root: true,
					icon: 'flaticon2-user-outline-symbol',
					page: '/user-list',
					translate: 'Kullanıcı Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'User-Type-List',
					root: true,
					icon: 'flaticon2-user-outline-symbol',
					page: '/userType-list',
					translate: 'Kullanıcı Türü Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Category-List',
					root: true,
					icon: 'flaticon2-indent-dots',
					page: '/category-list',
					translate: 'Kategori Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Department-List',
					root: true,
					icon: 'flaticon2-files-and-folders',
					page: '/department-list',
					translate: 'Departman Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Complaint-List',
					root: true,
					icon: 'flaticon2-indent-dots',
					page: '/complaint-list',
					translate: 'Şikayet Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					section: 'Şikayet Görüntüle',
					state: 'show'
				},
				{
					title: 'View-Complaint',
					root: true,
					icon: 'flaticon2-files-and-folders',
					page: '/view-complaint',
					translate: 'Şikayet Görüntüle',
					bullet: 'dot',
					state: 'show'
				},
			]
		},
		aside: {
			self: {},
			items: [
				{
					section: 'Yeni Kayıtlar',
					state: 'show'
				},
				{
					title: 'My-Page',
					root: true,
					icon: 'flaticon2-add-1',
					page: '/my-page',
					translate: 'My-Page',
					bullet: 'dot',
					state: 'hide'
				},
				{
					title: 'User-Add',
					root: true,
					icon: 'flaticon2-add-1',
					page: '/user-add',
					translate: 'Kullanıcı Ekle',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Category-Add',
					root: true,
					icon: 'flaticon2-writing',
					page: '/category-add',
					translate: 'Kategori Ekle',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Department-Add',
					root: true,
					icon: 'flaticon2-layers-2',
					page: '/department-add',
					translate: 'Departman Ekle',
					bullet: 'dot',
					state: 'show'
				},
				{
					section: 'Kayıtları Listele',
					state: 'show'
				},
				{
					title: 'User-List',
					root: true,
					icon: 'flaticon2-user-outline-symbol',
					page: '/user-list',
					translate: 'Kullanıcı Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'User-Type-List',
					root: true,
					icon: 'flaticon2-user-outline-symbol',
					page: '/userType-list',
					translate: 'Kullanıcı Türü Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Category-List',
					root: true,
					icon: 'flaticon2-indent-dots',
					page: '/category-list',
					translate: 'Kategori Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Department-List',
					root: true,
					icon: 'flaticon2-files-and-folders',
					page: '/department-list',
					translate: 'Departman Listele',
					bullet: 'dot',
					state: 'show'
				},
				{
					title: 'Complaint-List',
					root: true,
					icon: 'flaticon2-indent-dots',
					page: '/complaint-list',
					translate: 'Şikayet Listele',
					bullet: 'dot',
					state: 'show'
				},
			]
		},
	};

	public get configs(): any {
		return this.defaults;
	}
}
