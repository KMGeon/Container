#pragma once
#include "GameObject.h"
class Bullet : public GameObject //���ӿ�����Ʈ�� ��ӹ���
{
public:
	Bullet(COORD& startPos);
	~Bullet();

	void tick();
};

